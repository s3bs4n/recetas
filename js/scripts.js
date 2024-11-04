// src/main/resources/static/js/scripts.js

function verDetalles(nombreReceta) {
    // URL de la API con el nombre de la receta
    const url = `http://localhost:8082/api/recetas/buscar?nombre=${nombreReceta}`;

    // Realizar la solicitud AJAX
    fetch(url)
        .then(response => response.json())
        .then(data => {
            // Llenar los datos del modal con la información de la receta
            document.getElementById('modalTitulo').innerText = data.nombre;
            document.getElementById('modalTipoCocina').innerText = data.tipoCocina;
            document.getElementById('modalPaisOrigen').innerText = data.paisOrigen;
            document.getElementById('modalDificultad').innerText = data.dificultad;
            document.getElementById('modalTiempoCoccion').innerText = data.tiempoCoccion;
            document.getElementById('modalInstrucciones').innerText = data.instrucciones;

            // Limpiar ingredientes anteriores y agregar nuevos
            const ingredientesList = document.getElementById('modalIngredientes');
            ingredientesList.innerHTML = ''; // Limpiar lista
            data.ingredientes.forEach(ingrediente => {
                const li = document.createElement('li');
                li.innerText = ingrediente;
                ingredientesList.appendChild(li);
            });

            // Mostrar la imagen (base64) si está disponible
            if (data.fotografia) {
                document.getElementById('modalFotografia').src = `data:image/jpeg;base64,${data.fotografia}`;
                document.getElementById('modalFotografia').style.display = 'block';
            } else {
                document.getElementById('modalFotografia').style.display = 'none';
            }

            // Mostrar el modal
            document.getElementById('modalDetalles').style.display = 'block';
        })
        .catch(error => {
            console.error('Error al obtener los detalles de la receta:', error);
            alert('No se pudieron cargar los detalles de la receta.');
        });
}

function cerrarModal() {
    // Ocultar el modal
    document.getElementById('modalDetalles').style.display = 'none';
}
