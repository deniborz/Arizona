import MyCustomElement from './MyCustomElement.js';

class SandwichesList extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback()
    fetch('localhost:8080/den-travak/sandwiches')
      .then(response => response.json())
      .then(json => this.showSandwiches(json))
  }

  showSandwiches(json){
    let sandwichesList = this.shadowRoot.querySelector('#sandwiches')
    json.forEach(sandwich =>{
      let sandwichElement = document.createElement('div')
      sandwichElement.innerHTML = this.sandwichTemplate(sandwich)
      sandwichesList.appendChild(sandwichElement)
      let id = `${sandwich.id}-order`;
      this.shadowRoot.getElementById(id).addEventListener('click', () => document.querySelector('sandwich-app').dispatchEvent(new CustomEvent('order', {detail:sandwich})))
    })
  }

  sandwichTemplate(sandwich){
      return `
          <div class="list-group-item">
            <div>
              <h3>${sandwich.name}</h3>
              <p>${sandwich.ingredients}</p>
              <p>€ ${sandwich.price}</p>
            </div>
            <button type="button" class="btn btn-outline-success" id="${sandwich.id}-order">Order</button>
          </div>
      `
  }
  get template(){
    return `<div>
              <h2>All sandwiches</h2>
              <ul id="sandwiches" class="list-group">
              </ul>
            </div>`
  }
}
customElements.define('sandwich-list', SandwichesList);
