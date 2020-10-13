
CREATE TABLE employee_crud.employee (
    id uuid NOT NULL,
    email character varying(255),
    created_by character varying(255),
    created_on timestamp without time zone,
    updated_by character varying(255),
    updated_on timestamp without time zone,
    first_name character varying(255),
    last_name character varying(255),
    employee_id character varying(255),
    address_line1 character varying(255),
    address_line2 character varying(255),
    city character varying(255),
    country character varying(255),
    state character varying(255),
    zip_code character varying(255),
    is_active boolean
);