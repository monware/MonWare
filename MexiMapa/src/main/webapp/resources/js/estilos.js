/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   var colores = $('#colores').children();
   colores.each(function(){
       $(this).mouseover(function(){
           var color = $(this).css("background-color");
           console.log(color);
           $('#main').css({'background':color});
       });
   });
});

function alerta()
    {
    var mensaje;
    var opcion = confirm("¿Deseas Eliminar El Tema?");
    if (opcion === true) {
        mensaje = "Ha sido eliminado";
	} else {
	    mensaje = "Operacion Cancelada";
	}
	document.getElementById("ejemplo").innerHTML = mensaje;
}
    /**
    * Array con las imagenes que se iran mostrando en la web
    */
  /** 
var index = 0;

    var listaimg = ["https://cdn.pixabay.com/photo/2019/05/15/11/35/artwork-4204822_960_720.png", "https://i2.wp.com/www.andro-life.com/wp-content/uploads/2017/08/Wallpaper-5.png?ssl=1", "https://i.pinimg.com/originals/c4/13/59/c413599bc57f7596d75bea1775b14169.jpg","https://i.pinimg.com/originals/72/dc/33/72dc339da93a143ec1ed0d54143ecfea.jpg"];

$(function() {
  
    setInterval(changeImage, 3000);
  
});

function changeImage() {
  
 
   $('body').css("background-image",'url(' + listaimg[index] + ')');
                  
   index++;
                  
   if(index === listaimg.length)
      index = 0;
    
    
}
var images = ["https://cdn.pixabay.com/photo/2019/05/15/11/35/artwork-4204822_960_720.png",
              "https://i2.wp.com/www.andro-life.com/wp-content/uploads/2017/08/Wallpaper-5.png?ssl=1", 
              "https://i.pinimg.com/originals/c4/13/59/c413599bc57f7596d75bea1775b14169.jpg",
              "https://i.pinimg.com/originals/72/dc/33/72dc339da93a143ec1ed0d54143ecfea.jpg"
          ];

                    var bdy = document.getElementsByTagName("BODY")[0];
                    bdy.style.backgroundImage = "url(" + images[2] + ")";
                    var i = 0;
                    setInterval(function() {
                          bdy.style.backgroundImage = "url(" + images[i] + ")";
                          i = i + 1;
                          if (i == images.length) {
                            i =  0;
                          }
                    }, 5000);**/