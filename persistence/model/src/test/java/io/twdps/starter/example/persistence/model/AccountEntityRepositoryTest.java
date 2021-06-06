package io.twdps.starter.example.persistence.model;

import static org.assertj.core.api.Assertions.assertThat;

import io.twdps.starter.boot.test.data.spi.DataFactory;
import io.twdps.starter.example.data.account.model.AccountData;
import io.twdps.starter.example.data.account.provider.AccountDataFactory;
import io.twdps.starter.example.data.account.provider.AccountDataProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {AccountDataFactory.class, AccountDataProperties.class})
public class AccountEntityRepositoryTest {

  @Autowired private AccountEntityRepository modelEntityRepository;

  private AccountEntity entity;

  @Autowired private AccountDataFactory testData;

  private AccountData reference;
  private AccountData bogus;

  /** Setup test data. */
  @BeforeEach
  public void setup() {
    reference = testData.getNamedData(DataFactory.DEFAULT_NAME);
    bogus = testData.getNamedData("bogus");

    entity =
        new AccountEntity(
            reference.getUserName(),
            reference.getPii(),
            reference.getFirstName(),
            reference.getLastName());
  }

  /**
   * populate the tables with some tests data.
   *
   * @return one of the saved entities
   */
  public AccountEntity populate() {
    AccountEntity result = modelEntityRepository.save(entity);
    testData.getNamedDataCollection(DataFactory.DEFAULT_NAME).stream()
        .forEach(
            d -> {
              AccountEntity ref =
                  new AccountEntity(d.getUserName(), d.getPii(), d.getFirstName(), d.getLastName());
              modelEntityRepository.save(ref);
            });

    return result;
  }

  @Test
  public void setupValid() {
    assertThat(modelEntityRepository != null);
  }

  @Test
  public void createAndGetTest() {
    modelEntityRepository.save(entity);

    Optional<AccountEntity> retrievedEntity =
        modelEntityRepository.findByUserName(reference.getUserName());

    assertThat(retrievedEntity.isPresent());
    assertThat(retrievedEntity.get().getFirstName()).isEqualTo(reference.getFirstName());
  }

  @Test
  public void testFindByLastName() {
    populate();

    Page<AccountEntity> results =
        modelEntityRepository.findByLastName(reference.getLastName(), Pageable.unpaged());

    assertThat(results.getContent().size()).isEqualTo(2);
  }

  @Test
  public void testFindByLastNamePaged() {
    populate();

    Pageable pageable = PageRequest.of(0, 1);
    Page<AccountEntity> results =
        modelEntityRepository.findByLastName(reference.getLastName(), pageable);

    assertThat(results.getContent().size()).isEqualTo(1);
  }

  @Test
  public void testUpdateRecord() {
    AccountEntity saved = modelEntityRepository.save(entity);
    saved.setLastName(bogus.getLastName());

    AccountEntity updated = modelEntityRepository.save(saved);

    Page<AccountEntity> results =
        modelEntityRepository.findByLastName(reference.getLastName(), Pageable.unpaged());
    assertThat(results.getContent().size()).isEqualTo(0);
    Page<AccountEntity> bogonResults =
        modelEntityRepository.findByLastName(bogus.getLastName(), Pageable.unpaged());
    assertThat(bogonResults.getContent().size()).isEqualTo(1);
  }

  @Test
  public void testDeleteRecord() {
    AccountEntity saved = populate();

    modelEntityRepository.deleteById(saved.getId());

    Page<AccountEntity> results =
        modelEntityRepository.findByLastName(reference.getLastName(), Pageable.unpaged());
    assertThat(results.getContent().size()).isEqualTo(1);
  }

  @Test
  public void testFindAll() {
    populate();
    Page<AccountEntity> retrieved = modelEntityRepository.findAll(Pageable.unpaged());
    assertThat(retrieved.getContent().size()).isEqualTo(3);
  }

  @Test
  public void testFindAllPaged() {
    populate();
    Pageable pageable = PageRequest.of(0, 2);
    Page<AccountEntity> retrieved = modelEntityRepository.findAll(pageable);
    assertThat(retrieved.getContent().size()).isEqualTo(2);
  }
}
