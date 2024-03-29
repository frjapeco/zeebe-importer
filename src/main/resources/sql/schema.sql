CREATE TABLE IF NOT EXISTS process (
	id bigint PRIMARY KEY,
	tag varchar(255),
	version_number tinyint
);

CREATE TABLE IF NOT EXISTS process_instance (
	id bigint PRIMARY KEY,
	process_id bigint,
	FOREIGN KEY (process_id) REFERENCES process(id)
);
