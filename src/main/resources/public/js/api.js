function getSandwiches () {

    const div = document.getElementById('sandwiches');
    const url = 'http://193.191.177.8:10098/den-travak/sandwiches';

    fetch(url)
        .then((resp) => resp.json())
        .then(function(data) {
            let sandwiches = data;
            return sandwiches.map(function(sandwich) {
                let card =  document.createElement('div');
                let cardBody =  document.createElement('div');
                let cardTitle =  document.createElement('h5');
                let cardPrice =  document.createElement('h6');
                let cardText =  document.createElement('p');
                let cardButton =  document.createElement('a');

                cardTitle.innerHTML = sandwich.name;
                cardPrice.innerHTML = "€" + sandwich.price.toFixed(2);
                cardText.innerHTML = sandwich.ingredients;
                cardButton.innerHTML = "Go to checkout";

                cardTitle.classList.add("card-title");
                cardPrice.classList.add("card-subtitle");
                cardPrice.classList.add("mb-2");
                cardPrice.classList.add("text-muted");
                cardText.classList.add("card-text");
                cardButton.classList.add("card-link");

                cardButton.href = "checkout.html?id=" + sandwich.id;

                cardBody.appendChild(cardTitle);
                cardBody.appendChild(cardPrice);
                cardBody.appendChild(cardText);
                cardBody.appendChild(cardButton);

                cardBody.classList.add("card-body");
                card.appendChild(cardBody);
                card.classList.add("card");
                div.appendChild(card);
            })
        })
        .catch(function(error) {
            console.log(error);
        });
}

function getSandwich(id) {

    const div = document.getElementById('sandwich');
    const url = 'http://193.191.177.8:10098/den-travak/sandwiches/' + id;

    fetch(url)
        .then((resp) => resp.json())
        .then(function (data) {
            let sandwich = data;
            localStorage.setItem("sandwich", JSON.stringify(sandwich));
            let card = document.createElement('div');
            let cardBody = document.createElement('div');
            let cardTitle = document.createElement('h5');
            let cardPrice = document.createElement('h6');
            let cardText = document.createElement('p');
            let phoneNumber = document.createElement('input');
            let bread1 = document.createElement('input');
            let bread1Text = document.createElement('p');
            let bread2 = document.createElement('input');
            let bread2Text = document.createElement('p');
            let bread3 = document.createElement('input');
            let bread3Text = document.createElement('p');
            let cardButton = document.createElement('a');
            let emptyBreak00 = document.createElement('br');
            let emptyBreak0 = document.createElement('br');
            let emptyBreak1 = document.createElement('br');
            let emptyBreak2 = document.createElement('br');
            let emptyBreak3 = document.createElement('br');
            let emptyBreak4 = document.createElement('br');

            cardTitle.innerHTML = sandwich.name;
            cardPrice.innerHTML = "€" + sandwich.price.toFixed(2);
            cardText.innerHTML = sandwich.ingredients;
            cardButton.innerHTML = "Order now";
            bread1Text.innerHTML = "Boterhammekes";
            bread2Text.innerHTML = "Turkish bread";
            bread3Text.innerHTML = "Wrap";

            cardTitle.classList.add("card-title");
            cardPrice.classList.add("card-subtitle");
            cardPrice.classList.add("mb-2");
            cardPrice.classList.add("text-muted");
            cardText.classList.add("card-text");
            bread1Text.classList.add("radio-text");
            bread2Text.classList.add("radio-text");
            bread3Text.classList.add("radio-text");
            cardButton.classList.add("card-link");

            phoneNumber.type = "text";
            phoneNumber.id = "phoneNumber";
            phoneNumber.name = "phoneNumber";
            phoneNumber.placeholder = "Enter your phonenumber";
            bread1.type = "radio";
            bread1.name = "breadType";
            bread1.value = " Boterhammekes";
            bread1.id = "BOTERHAMMEKES";
            bread2.type = "radio";
            bread2.name = "breadType";
            bread2.value = " Turkish bread";
            bread1.id = "TURKISH_BREAD";
            bread3.type = "radio";
            bread3.name = "breadType";
            bread3.value = " Wrap";
            bread1.id = "WRAP";

            cardButton.href = "javascript:console.log('" + sandwich.name + ", " + sandwich.ingredients + ", " + sandwich.price + "')";
            cardButton.addEventListener("click", postOrder);
            cardBody.appendChild(cardTitle);
            cardBody.appendChild(cardPrice);
            cardBody.appendChild(cardText);
            cardBody.appendChild(phoneNumber);
            cardBody.appendChild(emptyBreak00);
            cardBody.appendChild(emptyBreak0);
            cardBody.appendChild(bread1);
            cardBody.appendChild(bread1Text);
            cardBody.appendChild(emptyBreak1);
            cardBody.appendChild(bread2);
            cardBody.appendChild(bread2Text);
            cardBody.appendChild(emptyBreak2);
            cardBody.appendChild(bread3);
            cardBody.appendChild(bread3Text);
            cardBody.appendChild(emptyBreak3);
            cardBody.appendChild(emptyBreak4);
            cardBody.appendChild(cardButton);

            cardBody.classList.add("card-body");
            card.appendChild(cardBody);
            card.classList.add("card");
            div.appendChild(card);

        })
        .catch(function (error) {
            console.log(error);
        })
}

function getActiveId() {
    let search_params = this.getAllUrlParams(window.location.href);
    console.log(search_params.id);
    return search_params.id;
}

function getAllUrlParams(url) {

    // get query string from url (optional) or window
    var queryString = url ? url.split('?')[1] : window.location.search.slice(1);

    // we'll store the parameters here
    var obj = {};

    // if query string exists
    if (queryString) {

        // stuff after # is not part of query string, so get rid of it
        queryString = queryString.split('#')[0];

        // split our query string into its component parts
        var arr = queryString.split('&');

        for (var i = 0; i < arr.length; i++) {
            // separate the keys and the values
            var a = arr[i].split('=');

            // set parameter name and value (use 'true' if empty)
            var paramName = a[0];
            var paramValue = typeof (a[1]) === 'undefined' ? true : a[1];

            // (optional) keep case consistent
            paramName = paramName.toLowerCase();
            if (typeof paramValue === 'string') paramValue = paramValue.toLowerCase();

            // if the paramName ends with square brackets, e.g. colors[] or colors[2]
            if (paramName.match(/\[(\d+)?\]$/)) {

                // create key if it doesn't exist
                var key = paramName.replace(/\[(\d+)?\]/, '');
                if (!obj[key]) obj[key] = [];

                // if it's an indexed array e.g. colors[2]
                if (paramName.match(/\[\d+\]$/)) {
                    // get the index value and add the entry at the appropriate position
                    var index = /\[(\d+)\]/.exec(paramName)[1];
                    obj[key][index] = paramValue;
                } else {
                    // otherwise add the value to the end of the array
                    obj[key].push(paramValue);
                }
            } else {
                // we're dealing with a string
                if (!obj[paramName]) {
                    // if it doesn't exist, create property
                    obj[paramName] = paramValue;
                } else if (obj[paramName] && typeof obj[paramName] === 'string'){
                    // if property does exist and it's a string, convert it to an array
                    obj[paramName] = [obj[paramName]];
                    obj[paramName].push(paramValue);
                } else {
                    // otherwise add the property
                    obj[paramName].push(paramValue);
                }
            }
        }
    }

    return obj;
}

function postOrder() {
    let sandwich = JSON.parse(localStorage.getItem("sandwich"));
    let breadType = '';
    let radios = document.getElementsByName('breadType');
    let phoneNumber = document.getElementById('phoneNumber').value;

    for (let i = 0, length = radios.length; i < length; i++)
    {
        if (radios[i].checked)
        {
            breadType = radios[i].value.substr(1);
            break;
        }
    }

    let data = {
        name: sandwich.name,
        sandwichId: sandwich.id,
        breadType: breadType,
        price: sandwich.price,
        mobilePhoneNumber: phoneNumber
    }

    console.log(data);

    if(breadType == "" || phoneNumber.trim() == "") {
        alert("Gelieve elk veld in te vullen");
    } else {
        const url = 'http://193.191.177.8:10098/den-travak/orders';

        fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(data)
        }).then(res => res.json())
            .then(response => {console.log('Success, your order is being prepared.');})
            .catch(error => console.error('Error:', error));
    }
}