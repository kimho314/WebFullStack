package fastcampus.spring.batch.part3;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Slf4j
public class Homework1ChunkBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public Homework1ChunkBatchConfiguration(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job homework1Job() throws Exception {
        return jobBuilderFactory.get("homework1Job")
                .incrementer(new RunIdIncrementer())
                .start(this.homework1Step(null))
                .build();
    }

    @Bean
    @JobScope
    public Step homework1Step(@Value("#{jobParameters[allow_duplicate]}") Boolean allowDuplicate) throws Exception {
        return stepBuilderFactory.get("homework1Step")
                .<Person, Person>chunk(10)
                .reader(itemReader())
                .processor(itemProcessor(ObjectUtils.isEmpty(allowDuplicate) ? Boolean.FALSE : allowDuplicate))
                .writer(itemWriter())
                .build();
    }

    private CompositeItemWriter<? super Person> itemWriter() throws Exception {
//        CompositeItemWriter<Person> compositeItemWriter = new CompositeItemWriter<>();
//        compositeItemWriter.setDelegates(Arrays.asList(insertItemWriter(), printItemWriter()));
//        return compositeItemWriter;

        return new CompositeItemWriterBuilder<Person>()
                .delegates(insertItemWriter(), printItemWriter())
                .build();
    }

    private ItemWriter<Person> printItemWriter() {
        return items -> items.forEach(person -> log.info(person.toString()));
    }

    private JpaItemWriter<Person> insertItemWriter() throws Exception {
        JpaItemWriter<Person> itemWriter = new JpaItemWriterBuilder<Person>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    private ItemProcessor<? super Person, ? extends Person> itemProcessor(Boolean allowDuplicate) {
        if (!allowDuplicate) {
            return item -> item;
        }
        log.info("allowDuplicate : {}", allowDuplicate);
        Map<String, Person> map = new HashMap<>();
        return item -> {
            if (map.containsValue(item)) {
                return null;
            }
            map.put(item.getName(), item);
            return item;
        };
    }

    private FlatFileItemReader<Person> itemReader() throws Exception {
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("name", "age", "address");
        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(fieldSet -> {
            String name = fieldSet.readString("name");
            String age = fieldSet.readString("age");
            String address = fieldSet.readString("address");

            return new Person(name, age, address);
        });

        FlatFileItemReader<Person> itemReader = new FlatFileItemReaderBuilder<Person>()
                .name("csvFileItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("test2.csv")) // resources directory 및에 있는 파일을 읽게 해줌
                .linesToSkip(1) // 첫번재 줄을 skip 하고 다음 줄부터 읽게 해줌
                .lineMapper(lineMapper)
                .build();
        itemReader.afterPropertiesSet();

        return itemReader;
    }
}
