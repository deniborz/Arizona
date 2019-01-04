import MyCustomElement from './MyCustomElement.js';
import './OrderList.js'

class SandwichAdmin extends MyCustomElement{
  connectedCallback(){
    super.connectedCallback()
    this.setupEventListeners()
  }

  setupEventListeners(){

  }

  get template(){
    return `
      <style>
        .hidden {display:none}
      </style>
      <order-list></sandwich-list>
    `
  }
}


customElements.define('sandwich-admin', SandwichAdmin);
