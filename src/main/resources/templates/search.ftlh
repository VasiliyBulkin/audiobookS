<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>

    <form method="get" action="/search">

        <div class="form-group row">
            <label for="filter" class="col-sm-2 col-form-label">Book name</label>
            <div class="col-sm-10">
                <input type="text" name="filter" id="filter" class="form-control" placeholder="book pattern"  value="${filter!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="filterAuthorName" class="col-sm-2 col-form-label">Author name</label>
            <div class="col-sm-10">
                <input type="text" name="filterAuthorName" id="filterAuthorName" class="form-control" placeholder="author pattern"  value="${filterAuthorName!''}"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="filterGenres" class="col-sm-2 col-form-label">Genres</label>
            <div class="col-sm-10">
                <input type="text" name="filterGenres" id="filterGenres" class="form-control" placeholder="genres pattern" value="${filterGenres!''}"/>
            </div>
        </div>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0">Order by</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio"  name="orderBy" id="orderByBookName" value="Name" />
                        <label class="form-check-label" for="orderByBookName">
                            Book
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="orderBy" id="orderByAuthorName"  value="Author" />
                        <label class="form-check-label" for= "orderByAuthorName">
                            Author
                        </label>
                    </div>
                    <div class="form-check disabled">
                        <input class="form-check-input" type="radio" name="orderBy" id="gridRadios3" value="Genre"/>
                        <label class="form-check-label" for="gridRadios3">
                            Genre
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>

        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>

<div class="table-responsive-md">
            <table class="table table-hover table-bordered">
               <thead class="thead-dark">
                   <tr>
                       <th>Name</th>
                       <th>Author</th>
                       <th>Genres</th>
                       <th>Cover</th>
                   </tr>
               </thead>
                <#list books as book>
                 <tbody>
                          <tr>
                              <td>${book.name!""}</td>
                              <td>${book.authorName!""}</td>
                              <td>${book.genresName!""}</td>
                              <td>
                                  <#if book.filename??> <!-- проверка на наличее поля filename, ??приведение к булеву типу -->
                                  <img class="img-fluid mx-auto d-block" alt="There could be an image" style="width:200px;height:200px;" src="/img/${book.filename} ">
                              </#if>
                              </td>
                          </tr>
                    <#else>
                          <tr>
                              <td>
                                  <div class="alert alert-warning" role="alert">
                                      No books found for this request
                                  </div>
                              </td>
                              <td></td>
                              <td></td>
                              <td></td>
                          </tr>
                 </tbody>
                </#list>
            </table>
</div>
</@c.page>