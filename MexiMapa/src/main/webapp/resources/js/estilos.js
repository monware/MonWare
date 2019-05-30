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
    
    /* Array con las imagenes que se iran mostrando en la web*/
    
  
var index = 1;

    var listaimg = ["https://st2.depositphotos.com/3608591/11259/v/950/depositphotos_112590448-stock-illustration-abstract-polygonal-techno-background-futuristic.jpg",
                    "https://st2.depositphotos.com/3608591/11531/v/950/depositphotos_115317346-stock-illustration-abstract-polygonal-techno-background-futuristic.jpg",
                    "https://st2.depositphotos.com/3608591/11215/v/950/depositphotos_112159022-stock-illustration-abstract-polygonal-techno-background-futuristic.jpg",
                    "https://st2.depositphotos.com/3608591/11259/v/950/depositphotos_112590250-stock-illustration-abstract-polygonal-techno-background-futuristic.jpg"
];

$(function() {
    $('body').css("background-image",'url(' + listaimg[1] + ')');
   
    setInterval(changeImage, 30000);
  
});

function changeImage() {
  
 
   $('body').css("background-image",'url(' + listaimg[index] + ')');
                  
   index++;
                  
   if(index === listaimg.length)
      index = 0;
    
    
}
/**
var images = ["https://wallhere.com/es/wallpaper/1357153",
             "https://r1.ilikewallpaper.net/iphone-se-wallpapers/download/9793/Circuit-Board-iphone-se-wallpaper-ilikewallpaper_com.jpg"
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