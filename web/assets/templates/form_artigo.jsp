<form method="POST" action="http://localhost:8080/AlphaBlog/CriaArtigo" class="form">
    <div class="form-group row">
        <input type="text" class="form-control form-control-md" name="titulo" placeholder="Título" required="required">
    </div>

    <div class="form-group row">
        <select name="categoria_id" class="form-control form-control-md">
            <option value="" disabled selected>Categoria</option> 
            <option value="3">Artes</option> 
            <option value="1">Política</option>
            <option value="2">Atualidade</option>
        </select>
    </div>
    <div class="form-group row">
        <textarea class="form-control form-control-md" placeholder="Conteudo" name="conteudo" required="required" rows="10"	></textarea>
    </div>				
    <div class="form-check">
        <input class="form-check-input" type="checkbox" name="liberar" value="true" id="articleReleaseCheckbox">
        <label class="form-check-label" for="articleReleaseCheckbox">
            Liberado para publicação
        </label>
    </div>

    <div class="horizontal-align-center">
        <button class="btn save-button" type="submit">Salvar</button>
    </div>
</form>