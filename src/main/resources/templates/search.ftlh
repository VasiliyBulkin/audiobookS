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
            <label for="genres" class="col-sm-2 col-form-label">Genres</label>
            <div class="col-sm-10">
                <input type="text" name="genres" id="genres" class="form-control" placeholder="genres pattern" />
                       <!--value="${genres!''}"-->
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-2">Order by</div>
            <div class="col-sm-10">
                <div class="form-check">
                    <input type="checkbox" name="orderByBookName" id="orderByBookName" class="form-check-input" value="checked" ${orderByBookName!''}/>
                    <label for="orderByBookName" class="form-check-label">
                        Book
                    </label>
                </div>
                <div class="form-check">
                    <input type="checkbox" name="orderByAuthorName" id="orderByAuthorName" class="form-check-input" value="checked" ${orderByAuthorName!''}/>
                    <label for="orderByAuthorName"class="form-check-label" >
                        Author
                </label>
                </div>
            </div>
        </div>
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