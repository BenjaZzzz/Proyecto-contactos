// Mostrar notificación con animación moderna
function showNotification(message, type = "info") {
    const notification = document.createElement("div");
    notification.className = `notification notification-${type}`;
    notification.innerHTML = `
        <i class="fas fa-${type === "success" ? "check-circle" : type === "error" ? "exclamation-circle" : "info-circle"}"></i>
        <span>${message}</span>
    `;

    if (!document.querySelector("#notification-styles")) {
        const styles = document.createElement("style");
        styles.id = "notification-styles";
        styles.textContent = `
            .notification {
                position: fixed;
                top: 20px;
                right: 20px;
                padding: 15px 20px;
                border-radius: 8px;
                color: white;
                font-weight: 500;
                z-index: 1001;
                display: flex;
                align-items: center;
                gap: 10px;
                animation: slideIn 0.3s ease;
                max-width: 400px;
                box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1),
                            0 4px 6px -2px rgba(0, 0, 0, 0.05);
            }
            .notification-success { background-color: #10b981; }
            .notification-error { background-color: #ef4444; }
            .notification-info { background-color: #3b82f6; }

            @keyframes slideIn {
                from { transform: translateX(100%); opacity: 0; }
                to { transform: translateX(0); opacity: 1; }
            }
        `;
        document.head.appendChild(styles);
    }

    document.body.appendChild(notification);

    // Ocultar y eliminar después de 3 segundos
    setTimeout(() => {
        notification.style.animation = "slideIn 0.3s ease reverse";
        setTimeout(() => {
            if (notification.parentNode) {
                notification.remove();
            }
        }, 300);
    }, 3000);
}

// Mostrar mensaje de backend si existe
document.addEventListener("DOMContentLoaded", function () {
    const flashDiv = document.getElementById("flash-message");
    if (flashDiv) {
        const message = flashDiv.getAttribute("data-message");
        const type = flashDiv.getAttribute("data-type") || "info";
        showNotification(message, type);
    }
});
