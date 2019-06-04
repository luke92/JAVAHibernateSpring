$(function(){
    $(".numeros").keydown(function(event)
    {
        var band = false;
        //Se fija si el la tecla ingresada va del 0 al 9 tanto del pad numerico
        //como de las teclas de numeros normales
        if((event.keyCode > 47 && event.keyCode < 58) || (event.keyCode > 95 && event.keyCode < 106))
        {
                band = true;
        }
        else
                if( event.keyCode == 37 || // Flecha izquierda
                        event.keyCode == 38 || // Flecha arriba
                        event.keyCode == 39 || // Flecha derecha
                        event.keyCode == 40 || // Flecha abajo
                        event.keyCode == 8 ||  // Backspace
                        event.keyCode == 11 || // tab vertical
                        event.keyCode == 9 ||  // tab horizontal
                        event.keyCode == 0 // caracter nulo
                  )
                {
                        band = true;
                }
       
        return band;
    });
});

$(function(){
    $(".letras").keydown(function(event)
    {
        var band = false;
        //Se fija si el la tecla ingresada va del 0 al 9 tanto del pad numerico
        //como de las teclas de numeros normales
        if((event.keyCode > 64 && event.keyCode < 91) || (event.keyCode == 192))
        {
                band = true;
        }
        else
                if( event.keyCode == 32 || // Barra espaciadora
                        event.keyCode == 37 || // Flecha izquierda
                        event.keyCode == 38 || // Flecha arriba
                        event.keyCode == 39 || // Flecha derecha
                        event.keyCode == 40 || // Flecha abajo
                        event.keyCode == 8 ||  // Backspace
                        event.keyCode == 11 || // tab vertical
                        event.keyCode == 9 ||  // tab horizontal
                        event.keyCode == 0 // caracter nulo
                  )
                {
                        band = true;
                }
       
        return band;
    });
});
