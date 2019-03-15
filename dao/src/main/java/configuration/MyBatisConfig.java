package configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages="com.lynu.yzshopping.mybatis.persistence")
@EnableTransactionManagement
public class MyBatisConfig {

	/*@Value("${mybatis.mapperLocations}")
	private String mapperLocations;
	
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;

	@Value("${mybatis.configLocation}")
	private String configLocation;
	*/
//	@Autowired
//	private JdbcProperties jdbcProperties;


	/*@Bean
	@Primary
	public DataSource masterDataSource() throws Exception {

		Properties props = new Properties();
		         props.put("driverClassName", jdbcProperties.getDriverClassName());
		         props.put("url", jdbcProperties.getUrl());
		        props.put("username", jdbcProperties.getUsername());
		        props.put("password", jdbcProperties.getPassword());
		        return DruidDataSourceFactory.createDataSource(props);
//		return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
//				.driverClassName(jdbcProperties.getDriverClassName()).url(jdbcProperties.getUrl())
//				.username(jdbcProperties.getUsername()).password(jdbcProperties.getPassword()).build();
	}


	@Bean()
	public DataSource slaveDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", jdbcProperties.getDsslave().getDriverClassName());
		props.put("url", jdbcProperties.getDsslave().getUrl());
		props.put("username", jdbcProperties.getDsslave().getUsername());
		props.put("password", jdbcProperties.getDsslave().getPassword());
		return DruidDataSourceFactory.createDataSource(props);
	}


	*//**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 *
	 *
	 *//*
	@Bean
	public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
										@Qualifier("slaveDataSource") DataSource slaveDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DatabaseType.masterDatasource, masterDataSource);
		targetDataSources.put(DatabaseType.slaveDatasource, slaveDataSource);

		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置为myTestDbDataSource
		return dataSource;
	}

	*//**
	 * 配置事务管理器
	 *//*
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactory createSqlSessionFactory(DynamicDataSource  dataSource) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(dataSource);
		fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		fb.setConfigLocation(new PathMatchingResourcePatternResolver().getResources(configLocation)[0]);
		fb.setTypeAliasesPackage(typeAliasesPackage);
		return fb.getObject();
	}*/
}
