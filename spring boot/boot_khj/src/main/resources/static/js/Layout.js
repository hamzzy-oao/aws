class AppHeader extends HTMLElement {
    async connectedCallback() {
        try {
            const response = await fetch('/include/header.html');
            if (response.ok) {
                this.innerHTML = await response.text();
            }
        } catch (error) {
            console.error('헤더 로드 실패:', error);
        }
    }
}

customElements.define('app-header', AppHeader);