let fusionBlock;
let suppBlock;
let sortBlock;
let imagesBlock;
let extractBlock;
let signBlock;
document.addEventListener("DOMContentLoaded", function() {
    fusionBlock = document.getElementById("fusion");
    suppBlock = document.getElementById('supp-pages');
    sortBlock = document.getElementById('sort-pages');
    imagesBlock = document.getElementById('image-to-pdf');
    extractBlock = document.getElementById('extract-images');
    signBlock = document.getElementById('sign-pdf');
});


function toggleSection(section) {
    if (section === 'fusion'){
        if(fusionBlock) {
            fusionBlock.classList.remove('hidden');
            suppBlock.classList.add('hidden');
            sortBlock.classList.add('hidden');
            imagesBlock.classList.add('hidden');
            extractBlock.classList.add('hidden');
            signBlock.classList.add('hidden');
        }
    }
    if (section === 'supp') {
        if(suppBlock) {
            fusionBlock.classList.add('hidden');
            suppBlock.classList.remove('hidden');
            sortBlock.classList.add('hidden');
            imagesBlock.classList.add('hidden');
            extractBlock.classList.add('hidden');
            signBlock.classList.add('hidden');
        }
    }
    if (section === 'sort') {
        if(sortBlock) {
            fusionBlock.classList.add('hidden');
            suppBlock.classList.add('hidden');
            sortBlock.classList.remove('hidden');
            imagesBlock.classList.add('hidden');
            extractBlock.classList.add('hidden');
            signBlock.classList.add('hidden');
        }
    }
    if (section === 'img'){
        if(imagesBlock) {
            fusionBlock.classList.add('hidden');
            suppBlock.classList.add('hidden');
            sortBlock.classList.add('hidden');
            imagesBlock.classList.remove('hidden');
            extractBlock.classList.add('hidden');
            signBlock.classList.add('hidden');
        }
    }
    if (section === 'extract') {
        if(extractBlock) {
            fusionBlock.classList.add('hidden');
            suppBlock.classList.add('hidden');
            sortBlock.classList.add('hidden');
            imagesBlock.classList.add('hidden');
            extractBlock.classList.remove('hidden');
            signBlock.classList.add('hidden');
        }
    }
    if (section === 'sign') {
        if(signBlock) {
            fusionBlock.classList.add('hidden');
            suppBlock.classList.add('hidden');
            sortBlock.classList.add('hidden');
            imagesBlock.classList.add('hidden');
            extractBlock.classList.add('hidden');
            signBlock.classList.remove('hidden');
        }
    }
}


