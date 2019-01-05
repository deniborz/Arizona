import MyCustomElement from './MyCustomElement.js'

class SandwichOrderConfirm extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback();
    this.setupEventListeners();
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
                <a onclick="this.saveRating(1)" class="btn btn-warning rating">1</a>
                <a onclick="this.saveRating(2)" class="btn btn-warning rating">2</a>
                <a onclick="this.saveRating(3)" class="btn btn-warning rating">3</a>
                <a onclick="this.saveRating(4)" class="btn btn-warning rating">4</a>
                <a onclick="this.saveRating(5)" class="btn btn-warning rating">5</a>
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

  saveRating(rating) {
      let recommendedSandwich = {
          emailAddress : "ronald.dehuysser@ucll.be",
          ratedItem : this.order.sandwichId,
          rating : rating
      };

      console.log(recommendedSandwich);

      fetch('/recommendation/recommend/', {
          method: "POST",
          headers: {
              "Content-Type": "application/json; charset=utf-8",
          },
          body: JSON.stringify(recommendedSandwich),
      })
          .then(resp => resp.json())
          .then(response => alert('Bedankt!'));
  };
}
customElements.define('sandwich-order-confirmation', SandwichOrderConfirm);
