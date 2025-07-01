CREATE TABLE patients (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
        first_name VARCHAR(100) NOT NULL,
        last_name VARCHAR(100) NOT NULL,
        age INT NOT NULL,
        PRIMARY KEY (id)
    );

    CREATE TABLE clinical_data (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        component_name VARCHAR(100) NOT NULL,
        component_value VARCHAR(100) NOT NULL,
        measured_date_time TIMESTAMP NOT NULL,
        patient_id BIGINT NOT NULL,
        CONSTRAINT fk_patient
            FOREIGN KEY (patient_id)
            REFERENCES patients(id)
    );

    INSERT INTO patients (first_name, last_name, age) VALUES
    ('John', 'Doe', 45),
    ('Jane', 'Smith', 38),
    ('Alice', 'Johnson', 29),
    ('Bob', 'Williams', 52),
    ('Emily', 'Brown', 34);

    INSERT INTO clinical_data (component_name, component_value, measured_date_time, patient_id) VALUES
    ('Blood Pressure', '120/80', CURRENT_TIMESTAMP, 1),
    ('Heart Rate', '72', CURRENT_TIMESTAMP, 1),
    ('Blood Pressure', '110/70', CURRENT_TIMESTAMP, 2),
    ('Heart Rate', '68', CURRENT_TIMESTAMP, 2),
    ('Blood Pressure', '115/75', CURRENT_TIMESTAMP, 3),
    ('Heart Rate', '70', CURRENT_TIMESTAMP, 3),
    ('Blood Pressure', '130/85', CURRENT_TIMESTAMP, 4),
    ('Heart Rate', '75', CURRENT_TIMESTAMP, 4),
    ('Blood Pressure', '118/78', CURRENT_TIMESTAMP, 5),
    ('Heart Rate', '69', CURRENT_TIMESTAMP, 5);