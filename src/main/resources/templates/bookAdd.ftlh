<#import "parts/common.ftlh" as c>

<@c.page>
<#list authors as author>
<div class="alert alert-info" role="alert">
    Form fo  ${author.authorname!""}
</div>
<form action="/author/add" method="post" enctype="multipart/form-data">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label"> Input name of book </label>
        <div class="col-sm-10">
            <input type="text" name="name" id="name" class="form-control"  placeholder="more then zero simbols" <#if messageNotOk??>value="${authorname!''}" </#if>>
        </div>
    </div>

    <div class="custom-file">
        <input type="file" class="custom-file-input" id="filecover" name="file">
        <label class="custom-file-label" for="filecover">Choose file</label>
    </div>

    <div class="form-group row">
        <div class="col-sm-2">Checkbox</div>
        <div class="col-sm-10">
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Detective fiction" class="form-check-input" value="Detective fiction"/>
                <label for="Detective fiction" class="form-check-label">
                    Detective fiction
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Crime" class="form-check-input" value="Crime"/>
                <label for="Crime" class="form-check-label">
                    Crime
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Science fiction" class="form-check-input" value="Science fiction"/>
                <label for="Science fiction" class="form-check-label">
                    Science fiction
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Post-apocalyptic" class="form-check-input" value="Post-apocalyptic"/>
                <label for="Post-apocalyptic" class="form-check-label">
                    Post-apocalyptic
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Distopia" class="form-check-input" value="Distopia"/>
                <label for="Distopia" class="form-check-label">
                    Distopia
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Cyberpunk" class="form-check-input" value="Cyberpunk"/>
                <label for="Cyberpunk" class="form-check-label">
                    Cyberpunk
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Fantasy" class="form-check-input" value="Fantasy"/>
                <label for="Fantasy" class="form-check-label">
                    Fantasy
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Romantic novel" class="form-check-input" value="Romantic novel"/>
                <label for="Romantic novel" class="form-check-label">
                    Romantic novel
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Western" class="form-check-input" value="Western"/>
                <label for="Western" class="form-check-label">
                    Western
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Horror" class="form-check-input" value="Horror"/>
                <label for="Horror" class="form-check-label">
                    Horror
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Classic" class="form-check-input" value="Classic"/>
                <label for="Classic" class="form-check-label">
                    Classic
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Fairy tale" class="form-check-input" value="Fairy tale"/>
                <label for="Fairy tale" class="form-check-label">
                    Fairy tale
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Fan fiction" class="form-check-input" value="Fan fiction"/>
                <label for="Fan fiction" class="form-check-label">
                    Fan fiction
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Folklore" class="form-check-input" value="Folklore"/>
                <label for="Folklore" class="form-check-label">
                    Folklore
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Historical fiction" class="form-check-input" value="Historical fiction"/>
                <label for="Historical fiction" class="form-check-label">
                    Historical fiction
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Humor" class="form-check-input" value="Humor"/>
                <label for="Humor" class="form-check-label">
                    Humor
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Mystery" class="form-check-input" value="Mystery"/>
                <label for="Mystery" class="form-check-label">
                    Mystery
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Thriller" class="form-check-input" value="Thriller"/>
                <label for="Thriller" class="form-check-label">
                    Thriller
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Erotic fiction" class="form-check-input" value="Erotic fiction"/>
                <label for="Erotic fiction" class="form-check-label">
                    Erotic fiction
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Biograpy" class="form-check-input" value="Biograpy"/>
                <label for="Biograpy" class="form-check-label">
                    Biograpy
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Autobiography" class="form-check-input" value="Autobiography"/>
                <label for="Autobiography" class="form-check-label">
                    Autobiography
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Essay" class="form-check-input" value="Essay"/>
                <label for="Essay" class="form-check-label">
                    Essay
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Instruction manual" class="form-check-input" value="Instruction manual"/>
                <label for="Instruction manual" class="form-check-label">
                    Instruction manual
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Journalism" class="form-check-input" value="Journalism"/>
                <label for="Journalism" class="form-check-label">
                    Journalism
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Memoir" class="form-check-input" value="Memoir"/>
                <label for="Memoir" class="form-check-label">
                    Memoir
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Reference book" class="form-check-input" value="Reference book"/>
                <label for="Reference book" class="form-check-label">
                    Reference book
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Self-help book" class="form-check-input" value="Self-help book"/>
                <label for="Self-help book" class="form-check-label">
                    Self-help book
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Textbook" class="form-check-input" value="Textbook"/>
                <label for="Textbook" class="form-check-label">
                    Textbook
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Encyclopedia" class="form-check-input" value="Encyclopedia"/>
                <label for="Encyclopedia" class="form-check-label">
                    Encyclopedia
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Dictionary" class="form-check-input" value="Dictionary"/>
                <label for="Dictionary" class="form-check-label">
                    Dictionary
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Popular science" class="form-check-input" value="Popular science"/>
                <label for="Popular science" class="form-check-label">
                    Popular science
                </label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="genrenames" id="Photograph" class="form-check-input" value="Photographr"/>
                <label for="Photograph" class="form-check-label">
                    Photograph
                </label>
            </div>

        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" class="btn btn-primary">Add book</button>
        </div>
    </div>
    <input type="hidden" value="${author.id}" name="authorId">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>

</#list>

</@c.page>








