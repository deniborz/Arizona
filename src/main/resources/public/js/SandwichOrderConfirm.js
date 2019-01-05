import MyCustomElement from './MyCustomElement.js'

class SandwichOrderConfirm extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback();
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
                <a onclick="saveRating(1, '${order.sandwichId}')" class="btn btn-warning rating">1</a>
                <a onclick="saveRating(2, '${order.sandwichId}')" class="btn btn-warning rating">2</a>
                <a onclick="saveRating(3, '${order.sandwichId}')" class="btn btn-warning rating">3</a>
                <a onclick="saveRating(4, '${order.sandwichId}')" class="btn btn-warning rating">4</a>
                <a onclick="saveRating(5, '${order.sandwichId}')" class="btn btn-warning rating">5</a>
                </br></br>
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

}
customElements.define('sandwich-order-confirmation', SandwichOrderConfirm);
