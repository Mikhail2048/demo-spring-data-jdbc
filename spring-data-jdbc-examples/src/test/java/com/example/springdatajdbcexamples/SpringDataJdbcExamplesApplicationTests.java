package com.example.springdatajdbcexamples;

import com.example.springdatajdbcexamples.models.many_to_x.Author;
import com.example.springdatajdbcexamples.models.many_to_x.Author.AuthorBookRef;
import com.example.springdatajdbcexamples.models.many_to_x.Book;
import com.example.springdatajdbcexamples.models.many_to_x.BookDetails;
import com.example.springdatajdbcexamples.models.one_to_x.Order;
import com.example.springdatajdbcexamples.models.one_to_x.OrderAttributes;
import com.example.springdatajdbcexamples.models.one_to_x.OrderFile;
import com.example.springdatajdbcexamples.models.one_to_x.Product;
import com.example.springdatajdbcexamples.models.simple.Person;
import com.example.springdatajdbcexamples.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJdbcTest
@AutoConfigureTestDatabase
@Sql(scripts = "classpath:schema/schema.sql")
class SpringDataJdbcExamplesApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void testPersistenceOfSimpleAggregate() {
        Person person = new Person();
        person.setAge(20);
        person.setFirstName("Mikhail");
        person.setLastName("Polivakha");

        Person first = personRepository.save(person);

        Assert.notNull(first.getId(), "The loaded entity's id must be there...");

        Optional<Person> personById = personRepository.findById(first.getId());

        Assert.notNull(personById, "Found by id person should be present...");

        Person second = personRepository.findByAgeBefore(25);

        Assert.notNull(second, "The loaded entity must be there...");
    }

    @Test
    void testOneToAnyMapping() {
        Order order = new Order();
        order.setId(10L);
        order.setPaid(true);
        order.setOrderAttributes(new OrderAttributes(12L, "I am alive", new Date(), 12L));
        order.setProduct(new Product(2L, "very specific product"));
        order.setOrderFiles(List.of(new OrderFile(1L, "firstFile"), new OrderFile(2L, "secondFile")));
        jdbcAggregateTemplate.insert(order);

        log.debug("------------------------------Trying to load aggregate------------------------------");

        Order result = jdbcAggregateTemplate.findById(10L, Order.class);
        Assert.notNull(result, "Result must be not null");
        Assert.notNull(result.getOrderAttributes(), "Result must be not null");
        Assert.notNull(result.getOrderFiles(), "Result must be not null");
        Assert.notNull(result.getProduct(), "Result must be not null");

        log.debug("------------------------------Trying to delete aggregate------------------------------");

        jdbcAggregateTemplate.deleteById(result.getId(), Order.class);
    }

    @Test
    void testManyToXMapping() {
        Book book = new Book();
        book.setId(14L);
        book.setName("Queen");
        book.setBookDetails(new BookDetails(16L, "I want to break free...", new Date()));
        book.setAmountOfPages(126);
        book.setAuthorRefs(List.of(new AuthorBookRef(52L, book.getId())));

        Author byId = jdbcAggregateTemplate.findById(52L, Author.class);

        jdbcAggregateTemplate.insert(book);

        Book bookInDb = jdbcAggregateTemplate.findById(book.getId(), Book.class);

        Assertions.assertThat(bookInDb.getAuthorRefs()).isNotNull();
        Assertions.assertThat(bookInDb.getBookDetails()).isNotNull();
    }
}