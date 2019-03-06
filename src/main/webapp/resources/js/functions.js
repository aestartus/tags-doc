function imgDecodeBase64(imagenBase64){
    var binaryImg = atob(imagenBase64);
    var length = binaryImg.length;
    var ab = new ArrayBuffer(length);
    var ua = new Uint8Array(ab);
    for (var i = 0; i < length; i++) {
        ua[i] = binaryImg.charCodeAt(i);
    }
    var blob = new Blob([ab], {
        type: "image/jpeg"
    });

    return  URL.createObjectURL(blob);
}

function generateBlob(imagenArray){
    var length = imagenArray.length;
    var ab = new ArrayBuffer(length);
    var ua = new Uint8Array(ab);
    for (var i = 0; i < length; i++) {
        ua[i] = imagenArray.charCodeAt(i);
    }
    var blob = new Blob([ab], {
        type: "image/jpeg"
    });

    return  URL.createObjectURL(blob);
}

function showImage(id){
    var imagen = document.getElementById("pagina"+id).value;
    var blob = imgDecodeBase64(imagen);
    console.log(blob);
    $("#"+id).html();
    $("#"+id).html("<div src=\""+blob+"\">");
}