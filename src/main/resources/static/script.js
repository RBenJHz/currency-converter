// URL de la API de conversión de monedas
const API_URL = 'http://localhost:8080/convert/';

// Variables de los elementos del DOM
const convertButton = document.getElementById('convertButton');
const copyButton = document.getElementById('copyButton');
const clearButton = document.getElementById('clearButton');
const resultDiv = document.getElementById('result');
const actionButtons = document.getElementById('action-buttons');
const historyContainer = document.getElementById('historyContainer'); // Contenedor del historial
const historyList = document.getElementById('history');

// Función para obtener el tipo de cambio y realizar la conversión
async function convertCurrency() {
    // Obtener los valores de entrada y normalizarlos
    const baseCurrency = document.getElementById('moneda-base').value;
    const targetCurrency = document.getElementById('moneda-objetivo').value;
    const amount = parseFloat(document.getElementById('monto').value);

    // Validar que todos los campos están llenos
    if (!baseCurrency || !targetCurrency || isNaN(amount)) {
        alert("Por favor, ingresa todos los datos.");
        return;
    }

    try {
        // Solicitar la tasa de cambio desde el backend de Spring Boot
        const response = await fetch(`${API_URL}${baseCurrency}?target=${targetCurrency}`);

        // Verificar si la solicitud fue exitosa
        if (!response.ok) {
            throw new Error("Error al obtener el tipo de cambio.");
        }

        // Convertir la respuesta a texto y luego a número (tasa de cambio)
        const rate = parseFloat(await response.text());

        // Verificar si la tasa de cambio es válida
        if (!isNaN(rate)) {
            const convertedAmount = amount * rate;

            // Mostrar el resultado de la conversión en el div correspondiente
            resultDiv.innerHTML = `${amount} ${baseCurrency} es igual a ${convertedAmount.toFixed(2)} ${targetCurrency}`;

            // Mostrar los botones de acción
            actionButtons.style.display = 'block';

            // Mostrar el historial de conversiones
            showHistory();

            // Agregar al historial de conversiones
            addToHistory(baseCurrency, targetCurrency, amount, convertedAmount.toFixed(2));
        } else {
            alert("Error al procesar el tipo de cambio recibido.");
        }
    } catch (error) {
        console.error("Error:", error.message);
        alert("Hubo un problema con la solicitud.");
    }
}

// Función para agregar la conversión al historial
function addToHistory(base, target, amount, convertedAmount) {
    // Crear un nuevo elemento de lista con el historial de la conversión
    const historyItem = document.createElement('li');
    historyItem.textContent = `${amount} ${base} = ${convertedAmount} ${target}`;
    historyList.appendChild(historyItem);
}

// Función para mostrar el historial de conversiones
function showHistory() {
    historyContainer.style.display = 'block';  // Mostrar el contenedor del historial
}

// Función para ocultar el historial de conversiones
function hideHistory() {
    historyContainer.style.display = 'none';  // Ocultar el contenedor del historial
}

// Función para copiar el resultado al portapapeles
function copyResult() {
    // Extrae solo el valor numérico del resultado
    const resultText = resultDiv.innerText;
    const valueToCopy = resultText.split(" es igual a ")[1].split(" ")[0];
    navigator.clipboard.writeText(valueToCopy)
        .then(() => alert("Valor de conversión copiado al portapapeles"))
        .catch((err) => alert("Error al copiar el resultado"));

    // Limpiar los campos de entrada (pero mantener el historial)
    document.getElementById('moneda-base').value = '';
    document.getElementById('moneda-objetivo').value = '';
    document.getElementById('monto').value = '';
    resultDiv.innerHTML = '';  // Limpiar el resultado
    actionButtons.style.display = 'none';  // Ocultar los botones de acción
}

// Función para limpiar los campos y el historial
function clearFields() {
    // Limpiar los campos de entrada, resultado y el historial
    document.getElementById('moneda-base').value = '';
    document.getElementById('moneda-objetivo').value = '';
    document.getElementById('monto').value = '';
    resultDiv.innerHTML = '';
    actionButtons.style.display = 'none';
    historyList.innerHTML = '';  // Limpiar el historial
    hideHistory(); // Ocultar historial al limpiar
}

// Asegurarse de que el historial esté oculto cuando se carga la página
window.addEventListener('load', function() {
    hideHistory();  // Llamar a la función que oculta el historial al cargar la página
});

// Asignar los eventos a los botones
convertButton.addEventListener('click', convertCurrency); // Ejecuta la conversión
copyButton.addEventListener('click', copyResult);         // Copia el resultado al portapapeles
clearButton.addEventListener('click', clearFields);       // Limpia todos los campos y el historial
