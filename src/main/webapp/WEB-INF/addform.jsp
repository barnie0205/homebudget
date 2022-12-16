<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
    <title>Dodaj wpływ lub wydatek</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<main>
    <h1>Nowy wpływ lub wydatek</h1>
    <form action="${pageContext.request.contextPath}/add" method="post">
        <fieldset>
            <legend>Dodaj nową pozycję</legend>
            <label class="keyboard-inputs">
                Opis wpływu / wydatku
                <input name="description" placeholder="np. Abonament Netflix">
            </label>
            <label class="keyboard-inputs">
                Wartość (zł)
                <input name="value" type="number" step="0.01" placeholder="np. 10.5">
            </label>
            <section>
                <label>
                    Wpływ
                    <input name="type" type="radio" value="INCOME">
                </label>
                <label>
                    Wydatek
                    <input name="type" type="radio" value="EXPENSE">
                </label>
            </section>
            <button>Zapisz</button>
        </fieldset>
    </form>
</main>
</body>
</html>