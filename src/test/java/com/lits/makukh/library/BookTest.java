package com.lits.makukh.library;

import com.lits.makukh.library.responses.Book;
import com.lits.makukh.library.responses.SearchBookResponse;
import com.lits.makukh.lits.LitsHttpClient;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.lits.makukh.library.TestHelper.authorizationHeader;

public class BookTest {

    @Test(groups = {"All", "Books"},
            description = "Verify book search by specified string")
    public void testSearchBookByQueryParam() {

        String queryParam = "mar";
        Response response = TestHelper.connect.GET(getSearchUrlByQuery(queryParam), authorizationHeader());
        SearchBookResponse searchBookResponse = LitsHttpClient.convert(response, SearchBookResponse.class);
        SearchBookResponse.Hit hit = searchBookResponse.getHits().get(0);
        Assert.assertTrue(hit.get_highlightResult().getName().getMatchedWords().length > 0);
        Assert.assertTrue(hit.getName().toLowerCase().contains(queryParam));
    }

    @Test(groups = {"All", "Books"},
            description = "Verify book search by not existing string")
    public void testBookNotFoundByQueryParam(){

        String queryParam = "notexistingstringforsearch";
        Response response = TestHelper.connect.GET(getSearchUrlByQuery(queryParam), authorizationHeader());
        SearchBookResponse searchBookResponse = LitsHttpClient.convert(response, SearchBookResponse.class);
        Assert.assertEquals(searchBookResponse.getHits().size(), 0);

    }

    @Test (groups = {"All", "Books"},
            description = "Verify book search by ISBN")
    public void testSearchBookByIsbn() {

        String isbn = "9781785031137";
        Response response = TestHelper.connect.GET(getSearchUrlByIsbn(isbn), authorizationHeader());
        Book book = LitsHttpClient.convert(response, Book.class);
        Assert.assertEquals(book.getIsbn(), isbn);
    }

    @Test(groups = {"All", "Books"},
            description = "Verify book search by not existing isbn")
    public void testBookNotFoundByIsbn(){
        String isbn = "notexistingisbn";
        Response response = TestHelper.connect.GET(getSearchUrlByIsbn(isbn), authorizationHeader());
        Assert.assertEquals(response.code(), 404);
    }

    @Test(groups = {"All", "Books"},
            description = "Verify book creation")
    public void testCreateBook(){

        String expectedIsbn ="LNK" + Math.random();
        Book bookBody = new Book();
        bookBody.setAuthor("LNK Author");
        bookBody.setDescription("LNK book description");
        bookBody.setIsbn(expectedIsbn);
        bookBody.setName("LNK book name");
        bookBody.setPublishDate("2020");
        bookBody.setPublisher("LNK publisher");

        Response response = TestHelper.connect.POST(getCreateBookUrl(),authorizationHeader(),bookBody);
        Book bookResponse = LitsHttpClient.convert(response,Book.class);
        Assert.assertEquals(response.code(), 200);
        Assert.assertEquals(bookResponse.getIsbn(), expectedIsbn);

    }

    @Test(groups = {"All", "Books"},
    description = "Verify book with the same isbn is not added")
    public void testCreateBookFailed(){
        Book bookBody = new Book();
        bookBody.setAuthor("LNK Author");
        bookBody.setDescription("LNK book description");
        bookBody.setIsbn("LNK123");
        bookBody.setName("LNK book name");
        bookBody.setPublishDate("2020");
        bookBody.setPublisher("LNK publisher");
        TestHelper.connect.POST(getCreateBookUrl(),authorizationHeader(),bookBody);
        Response response = TestHelper.connect.POST(getCreateBookUrl(),authorizationHeader(),bookBody);
        Assert.assertEquals(response.code(), 400);

    }


    private String getSearchUrlByQuery(String queryParam) {
        return TestHelper.baseUrl + "/v1/search?q=" + queryParam;
    }

    private String getSearchUrlByIsbn(String isbn) {
        return TestHelper.baseUrl + "/v1/books/" + isbn;
    }

    private String getCreateBookUrl(){
        return TestHelper.baseUrl + "/v1/books";
    }
}
