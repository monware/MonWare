/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

<<<<<<< HEAD

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


=======
>>>>>>> 7d834285213046ab4212e9f26ed14c9c80a61473
$(document).ready(function(){
   var colores = $('#colores').children();
   colores.each(function(){
       $(this).mouseover(function(){
           var color = $(this).css("background-color");
           console.log(color);
           $('#main').css({'background':color});
       });
   });
//   initMap();
<<<<<<< HEAD
});

//                
=======
});
>>>>>>> 7d834285213046ab4212e9f26ed14c9c80a61473
