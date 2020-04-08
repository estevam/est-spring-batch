# est-spring-batch
Spring batch demo using postgresql


```SQL
CREATE TABLE cms_user
(
    id_user bigint NOT NULL DEFAULT nextval('cms_user_id_user_seq'::regclass),
    username character varying(50) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT cms_user_pkey PRIMARY KEY (id_user)
)
```
