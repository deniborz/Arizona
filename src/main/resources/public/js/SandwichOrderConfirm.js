import MyCustomElement from './MyCustomElement.js'

class SandwichOrderConfirm extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback()
      this.addEventListeners()
  }

  orderTemplate(order){
      return `
            <div>
              <h3>${order.name}</h3>
              <p>${order.breadType}</p>
              <p>€ ${order.price}</p>
            </div>
            <div>
                <p>Rating geven:</p>
                <button data-rating="1" class="btn btn-warning rating">1</button>
                <button data-rating="2" class="btn btn-warning rating">2</button>
                <button data-rating="3" class="btn btn-warning rating">3</button>
                <button data-rating="4" class="btn btn-warning rating">4</button>
                <button data-rating="5" class="btn btn-warning rating">5</button>
                </br>
                <a href="index.html">Terug naar overzicht</a>
            </div>
      `
  }
  
  get template(){
    return `<div id="order-confirm">
              <p>Your order has been succesfully added!</p>
            </div>`
  }

  setOrder(order){
    let orderElement = document.createElement('div')
    orderElement.innerHTML = this.orderTemplate(order)
    this.shadowRoot.getElementById('order-confirm').appendChild(orderElement)
  }

  addEventListeners() {
      this.shadowRoot.querySelectorAll('button.rating')
          .forEach(button => button.addEventListener('click', e => {

              let recommendedSandwich = {
                  emailAddress : this.order.mobilePhoneNumber,
                  ratedItem : this.order.sandwichId,
                  rating : button.dataset.rating
              };

              fetch('/recommendation/recommend/', {
                  method: "POST",
                  headers: {
                      "Content-Type": "application/json; charset=utf-8",
                  },
                  body: JSON.stringify(recommendedSandwich),
              })
                  .then(resp => resp.json())
                  .then(response => alert('Bedankt!'));
          }));
  }
}
customElements.define('sandwich-order-confirmation', SandwichOrderConfirm);
