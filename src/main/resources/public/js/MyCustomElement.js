export default class MyCustomElement extends HTMLElement{
  connectedCallback () {
    this.initShadowDom()
  }

  initShadowDom(){
    if(this.shadowRoot) return
    let shadowRoot = this.attachShadow({mode: 'open'})
    shadowRoot.innerHTML = this.template
    shadowRoot.insertBefore(document.getElementById('styletemplate').content.cloneNode(true), shadowRoot.childNodes[0]);
    $(shadowRoot).bootstrapMaterialDesign()
  }

  get template() {
    return "No template!"
  }
}
