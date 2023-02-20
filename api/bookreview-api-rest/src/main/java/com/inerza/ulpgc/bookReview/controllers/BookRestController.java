package com.inerza.ulpgc.bookReview.controllers;

import com.inerza.ulpgc.bookReview.core.persistence.IBookService;
import com.inerza.ulpgc.bookReview.model.dto.BookDTO;
import com.inerza.ulpgc.bookReview.model.entities.Book;
import com.inerza.ulpgc.bookReview.model.mappers.BookMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Tag(description = "Books to be reviewed.", name = "Book Resource")
@RestController
@RequestMapping("books")
public class BookRestController {
    
    @Autowired
    private IBookService bookService;

    @Operation(summary = "Get books", description = "Provides all available books list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }) })
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<BookDTO> getBooks(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam() String sortDir,
            @RequestParam() String sort) {

        List<Book> books = bookService.getBookList(page, size, sortDir, sort);
        return books.stream()
          .map(x -> BookMapper.INSTANCE.convertToDto(x))
          .collect(Collectors.toList());
    }

    @Operation(summary = "Create a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }) })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.convertToEntity(bookDTO);
        Book bookCreated = bookService.createBook(book);
        return BookMapper.INSTANCE.convertToDto(bookCreated);
    }

    @Operation(summary = "Get a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }) })
    @GetMapping(value = "/{id}")
    @ResponseBody
    public BookDTO getBook(@PathVariable("id") Long id) {
        return BookMapper.INSTANCE.convertToDto(bookService.getBookById(id));
    }

    @Operation(summary = "Update a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }) })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {
        if (!Objects.equals(id, bookDTO.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }

        Book book = BookMapper.INSTANCE.convertToEntity(bookDTO);
        bookService.updateBook(book);
    }

    @Operation(summary = "Delete a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}",
                    content = { @Content(examples = { @ExampleObject(value = "") }) }) })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
