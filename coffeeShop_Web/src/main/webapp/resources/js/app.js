"use strict";

$(function () {
    console.log("hello ha");
    $('#btnClearOrder').click(function(e){
        e.preventDefault();
        $.ajax("/orders/delete", {"type": "DELETE"}).done(ajaxSuccess).fail(ajaxFail);
    });
    $('#btnCheckout').click(function (e) {
        e.preventDefault();


    })


});
function removeFromCart(productId){
    $.ajax("/orders/remove/" + productId, {"type": "PUT"}).done(ajaxSuccess).fail(ajaxFail);
}

function removePerson(personId){
    console.log("in remove person");
    $.ajax("/persons/delete/" + personId, {"type": "DELETE"}).done(ajaxRemovePersonSuccess).fail(ajaxFail);
}
function removeProduct(productId){
    console.log("in remove person");
    $.ajax("/products/delete/" + productId, {"type": "DELETE"}).done(ajaxRemoveProductSuccess).fail(ajaxFail);
}
function ajaxRemoveProductSuccess(){
    console.log("in remove product success");
    window.location.href = "";
}
function ajaxRemovePersonSuccess(){
    console.log("in remove person success");
    window.location.href = "persons";
}
function ajaxSuccess(){
    window.location.href = "orders";
}
function ajaxFail(){

}