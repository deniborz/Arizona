function saveRating(rating, order) {
    let recommendedSandwich = {
        emailAddress : "ronald.dehuysser@ucll.be",
        ratedItem : order.sandwichId,
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