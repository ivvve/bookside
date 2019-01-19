window.onload = () => {
    bookDetail.init();
}

const bookDetail = {
    bookDetailModalButton: null,
    bookDetailModal: null,
    bookDetailInfoTable: null,
    bookList: null,

    init() {
        this.bookDetailModalButton = document.getElementById("bookDetailModalButton");
        this.bookDetailModal = document.getElementById("bookDetailModal");
        this.bookDetailInfoTable = document.getElementById("bookDetailInfoTable");
        this.bookList = document.querySelectorAll("#bookList .bookDetailButton");
    },

    onBookClick(target) {
        const isbn = this.getIsbnFromData(target);

        this.getBookDetailInfoAndDraw(isbn);
    },

    getIsbnFromData(target) {
        return target.dataset.isbn.split(" ")[0];
    },

    getBookDetailInfoAndDraw(isbn) {
        fetch(`/book/detail?isbn=${isbn}`)
            .then(response => response.json())
            .then(json => {
                const bookDeatilJson = json.channel.item
                this.drawBookDetailInfoIntoModal(bookDeatilJson);
            });
    },

    drawBookDetailInfoIntoModal(bookDetailJson) {
        this.bookDetailModal.querySelector(".title").innerText = bookDetailJson.title;
        this.bookDetailModal.querySelector(".image").src = bookDetailJson.image;
        this.bookDetailModal.querySelector(".author").innerText = bookDetailJson.author;
        this.bookDetailModal.querySelector(".publisher").innerText = bookDetailJson.publisher;
        this.bookDetailModal.querySelector(".originalPrice").innerText = bookDetailJson.price;
        this.bookDetailModal.querySelector(".discountPrice").innerText = bookDetailJson.discount;
        this.bookDetailModal.querySelector(".isbn").innerText = bookDetailJson.isbn;

        this.showBookDetailModal();
    },

    showBookDetailModal() {
        this.bookDetailModalButton.click();
    }
}