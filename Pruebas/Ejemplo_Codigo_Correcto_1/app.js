const inputs = document.querySelectorAll( 'input' );
const selects = document.querySelectorAll( 'select' );

const handleSubmit = ( evt ) => {
  evt.preventDefault();

  let mensaje = '';
  const valores = {};

  inputs.forEach( ( input ) => valores[ input.id ] = input.value );
  selects.forEach( ( select ) => valores[ select.id ] = select.value );

  for( const key in valores ) mensaje = mensaje.concat( `${ key } : ${ valores[ key ] }\n` );

  alert( mensaje );
};

document.querySelector( 'form' ).addEventListener( 'submit', handleSubmit );
