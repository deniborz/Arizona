function formatDate(date){
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
    return result;
}

function saveRating(rating, sandwichId) {
    let recommendedSandwich = {
        emailAddress : "ronald.dehuysser@ucll.be",
        ratedItem : sandwichId,
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

function updateOrders() {
    let date = this.formatDate(new Date());

    fetch('http://193.191.177.8:10368/den-travak/orders?date=' + date).then(resp => resp.json())
        .then(data => {
            for(let i = 0; i < data.length; i++){
                fetch('http://193.191.177.8:10368/den-travak/orders/' + data[i].id, {
                    method: 'PUT',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        "id": data[i].id,
                        "sandwichId": data[i].sandwichId,
                        "name": data[i].name,
                        "breadType": data[i].breadType,
                        "creationDate": data[i].creationDate,
                        "price": data[i].price,
                        "mobilePhoneNumber": data[i].mobilePhoneNumber,
                        "printed": true
                    }),
                });
            }
        })
}