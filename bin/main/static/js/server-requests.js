const genreAddButton = document.querySelector(".add-genre button");
const genreAddInput = document.querySelector(".add-genre input");
const genresList = document.querySelector(".genres-list ul");
const genreRemoveButton = document.querySelector(".remove-genre button");
const genreRemoveInput = document.querySelector(".remove-genre input");

const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
    if (xhr.readyState === 4 && xhr.status === 200){
        const res=xhr.responseText;
        genresList.innerHTML = res;
    }
}
genreAddButton.addEventListener("click", function(){
    postGenres(genreAddInput.value);
    genreAddInput.value= "";
})

genreRemoveButton.addEventListener("click", function(){
    var askToConfirm = confirm("Are you sure you want to delete this genre from the database?");
    if (askToConfirm){
        removeGenre(genreRemoveInput.value);
        genreRemoveInput.value = "";  
    }
   
})

function postGenres(genreName){
    xhr.open("POST", "/genres/" + genreName, true);
    xhr.send();
}
function removeGenre(genreName){
    xhr.open("POST", "/genres/remove/" + genreName, true);
    xhr.send();
}