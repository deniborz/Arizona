import MyCustomElement from './MyCustomElement.js'

class SandwichOrderConfirm extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback();
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.innerHTML = `saveRating(rating) {
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
  }`
    this.shadowRoot.appendChild(script);
    //this.setupEventListeners();
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
                <a onclick="saveRating(1)" class="btn btn-warning rating">1</a>
                <a onclick="saveRating(2)" class="btn btn-warning rating">2</a>
                <a onclick="saveRating(3)" class="btn btn-warning rating">3</a>
                <a onclick="saveRating(4)" class="btn btn-warning rating">4</a>
                <a onclick="saveRating(5)" class="btn btn-warning rating">5</a>
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

  setupEventListeners(){
      const ratingButtons = this.shadowRoot.querySelector(".rating")
      console.log("rating", ratingButtons);
      [].forEach.call(ratingButtons, function (button) {console.log("button", button)});
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
  }
}
customElements.define('sandwich-order-confirmation', SandwichOrderConfirm);
