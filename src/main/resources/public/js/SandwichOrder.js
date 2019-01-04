import MyCustomElement from './MyCustomElement.js'

class SandwichOrder extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback()
    this.setupEventListeners()
    var script = document.createElement('script');
    script.src = 'http://code.jquery.com/jquery-1.11.0.min.js';
    script.type = 'text/javascript';
    this.shadowRoot.appendChild(script);
  }

  setSandwich(sandwich){
    let sandwichElement = document.createElement('div')
    sandwichElement.innerHTML = this.sandwichTemplate(sandwich);
    this.shadowRoot.getElementById('order').insertBefore(sandwichElement, this.shadowRoot.getElementById('order').firstChild)
    //this.shadowRoot.getElementById('confirm').addEventListener('click', () => document.querySelector('sandwich-app').dispatchEvent(new CustomEvent('confirm', {detail:sandwich})))
    this.shadowRoot.getElementById('confirm').addEventListener('click', () => this.confirm(sandwich) )
  }

  confirm(sandwich){
    let mobilePhoneNumber = this.shadowRoot.getElementById('phoneNumber').value
    let breadType = this.shadowRoot.querySelector('input[name="breadType"]:checked').value
    let order = {
      mobilePhoneNumber: mobilePhoneNumber,
      breadType: breadType,
      sandwichId: sandwich.id,
      price: sandwich.price,
      name: sandwich.name
    }
    this.dispatchEvent(new CustomEvent('confirm', {detail:order}))
  }

  sandwichTemplate(sandwich){
      return `
            <div>
              <h3>${sandwich.name}</h3>
              <p>${sandwich.ingredients}</p>
              <p>€ ${sandwich.price}</p>
            </div>
      `
  }

  get template(){
    return `<div id="order">
              <input type="radio" name="breadType" value="Turkish bread" checked="checked"> Turkish bread
              <input type="radio" name="breadType" value="Wrap"> Wrap
              <input type="radio" name="breadType" value="Boterhammekes"> Boterhammekes
              <label for="phoneNumber">Phonenumber:</label>
              <input type="text" name="phoneNumber" id="phoneNumber"/>
              <button id="confirm">Confirm order</button>
            </div>`
  }

  setupEventListeners(){
    this.addEventListener('confirm', (e) => this.confirmOrder(e.detail))
  }

  confirmOrder(order){
    this.postJSON('http://localhost:8080/orders', order, () => this.successOrder(order), () => this.failOrder())
  }

  successOrder(order){
    document.querySelector('sandwich-app').dispatchEvent(new CustomEvent('confirmation', {detail:order}))
  }

  failOrder(){
    console.log('TODO: failed to add order!')
  }

  postJSON(url, data, successCallback, errCallback) {
      return $.ajax({
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      },
      'type': 'POST',
      'url': url,
      'data': JSON.stringify(data),
      'dataType': 'json',
      'success': successCallback,
      'error': errCallback
      });
  };
}
customElements.define('sandwich-order', SandwichOrder);
