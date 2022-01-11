package learn.jpa.experiment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            items.add(createItem(i));
        }
        itemRepository.saveAll(items);
    }

    @Test
    void findAllByNameContaining() throws Exception {
        itemRepository.findAllByNameContaining("1", PageRequest.of(1, 3))
                .forEach(System.out::println);
    }

    @Test
    void readAllByNameContaining() throws Exception {
        itemRepository.readAllByNameContaining("1", PageRequest.of(1, 3))
                .forEach(System.out::println);
    }

    @Test
    void findByNameContaining() throws Exception {
        itemRepository.findByNameContaining("1")
                .forEach(System.out::println);
    }

    private Item createItem(int itemName) {
        return Item.builder()
                .name("item" + itemName)
                .description("item description")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
