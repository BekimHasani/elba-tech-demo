
CREATE TABLE department (

    id BIGINT PRIMARY KEY,
    dep_name VARCHAR(20) UNIQUE NOT NULL,
    dep_leader VARCHAR(20),
    dep_phone_number VARCHAR(30)
);

CREATE SEQUENCE department_id_seq
    OWNED BY department.id;

ALTER TABLE department ALTER id
    SET DEFAULT nextval('department_id_seq');

CREATE TABLE employee (

    id BIGINT PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    manager VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    department VARCHAR(20),
    phone_number VARCHAR(20),
    address VARCHAR(70),
    is_active BOOLEAN,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
 );

CREATE SEQUENCE employee_id_seq
    OWNED BY employee.id;

ALTER TABLE employee ALTER id
    SET DEFAULT nextval('employee_id_seq');