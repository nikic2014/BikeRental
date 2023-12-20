var el = document.getElementsByClassName('card');

for(var i=0; i < el.length; i++){
    el[i].children[2].style.overflow='hidden';
    el[i].children[2].style.height='0';
    el[i].children[2].style.opacity='0';
}

//for(var i=0; i < el.length; i++){
//    el[i].addEventListener('mouseenter', showSub, false);
//    el[i].addEventListener('mouseleave', hideSub, false);
//}
//
//function showSub(){
//    this.children[2].style.height='auto';
//    this.children[2].style.opacity='1';
//    this.children[2].style.overflow='visible';
//    this.children[2].style.backgroundColor=this.style.backgroundColor;
//}
//
//function hideSub(){
//    this.children[2].style.height='0';
//    this.children[2].style.opacity='0';
//    this.children[2].style.overflow='hidden';
//}
