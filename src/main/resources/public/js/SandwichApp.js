import MyCustomElement from './MyCustomElement.js';
import './sandwichesList.js';
import './SandwichOrder.js';
import './SandwichOrderConfirm.js';

class SandwichApp extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback()
    this.setupEventListeners()
  }

  setupEventListeners(){
    this.addEventListener('order', (e) => this.orderSandwich(e.detail))
    this.addEventListener('confirmation', (e) => this.confirmationOrder(e.detail))
  }

  get template(){
    return `
      <style>
        .hidden {display:none}
      </style>
      <sandwich-list></sandwich-list>
      <sandwich-order class="hidden"></sandwich-order>
      <sandwich-order-confirmation class="hidden"></sandwich-order-confirm>
    `
  }

  orderSandwich(sandwich){
    this.shadowRoot.querySelector('sandwich-list').classList.add('hidden')
    this.shadowRoot.querySelector('sandwich-order').classList.remove('hidden')
    this.shadowRoot.querySelector('sandwich-order').setSandwich(sandwich)
  }

  confirmationOrder(order){
    this.shadowRoot.querySelector('sandwich-order').classList.add('hidden')
    this.shadowRoot.querySelector('sandwich-order-confirmation').classList.remove('hidden')
    this.shadowRoot.querySelector('sandwich-order-confirmation').setOrder(order)
  }
}


customElements.define('sandwich-app', SandwichApp);
