import MyCustomElement from './MyCustomElement.js';

class OrderList extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback()
    let date = this.formatDate(new Date())
    fetch(`http://193.191.177.8:10098/den-travak/orders?date=${date}`)
      .then(response => response.json())
      .then(json => this.showOrders(json))
  }

  showOrders(json){
    console.log(json)
    let ordersList = this.shadowRoot.querySelector('#orders')
    json.forEach(order =>{
      let orderElement = document.createElement('div')
      orderElement.innerHTML = this.orderTemplate(order)
      ordersList.appendChild(orderElement)
    })
  }

 formatDate(date){
    let result = '';
    result += date.getFullYear() + '-'
    if(date.getMonth() > 8){
      result += date.getMonth() + 1 + '-'
    }else{
      result += '0' + (date.getMonth() + 1) + '-'
    }
    if(date.getDate() > 9){
      result += date.getDate()
    }else{
      result += '0' + date.getDate()
    }
    return result
 }

  orderTemplate(order){
      let printed = "not printed";
      if(order.printed == true) printed = "printed";
      return `
          <div>
            <h5>${order.name}</h5>
            <p>${order.mobilePhoneNumber}</p>
            <p>${printed}</p>
          </div>
      `
  }
  get template(){
    return `<div id="orders">
              <h3>Today's orders</h3>
            </div>
            <a onclick="updateOrders()" href="http://193.191.177.8:10098/den-travak/download" class="btn btn-outline-success">Download</a>`
  }
}

customElements.define('order-list', OrderList);
