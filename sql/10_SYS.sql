PURGE DBA_RECYCLEBIN;
ALTER PLUGGABLE DATABASE FREEPDB1 CLOSE IMMEDIATE;
ALTER PLUGGABLE DATABASE FREEPDB1 OPEN UPGRADE;
ALTER SESSION SET CONTAINER=FREEPDB1;
ALTER SYSTEM SET MAX_STRING_SIZE=EXTENDED;
@?/rdbms/admin/utl32k.sql;
ALTER PLUGGABLE DATABASE FREEPDB1 CLOSE;
ALTER PLUGGABLE DATABASE FREEPDB1 OPEN;
ALTER SESSION SET CONTAINER=FREEPDB1;
@?/env.txt
GRANT ALL PRIVILEGES TO &APP_USER;
GRANT SELECT ON SYS.DBA_JOBS_RUNNING TO &APP_USER;
GRANT SELECT ON SYS.DBA_JOBS TO &APP_USER;
BEGIN
  DBMS_NETWORK_ACL_ADMIN.CREATE_ACL (
    acl          => 'hello_utl_http_permissions.xml', -- Název souboru ACL
    description  => 'ACL for user &APP_USER to allow all network connections',
    principal    => '&APP_USER',                            -- Uživatel, kterému dáváme oprávnění
    is_grant     => TRUE,                               -- TRUE pro GRANT, FALSE pro DENY
    privilege    => 'connect',                          -- Povolujeme připojení
    start_date   => NULL,
    end_date     => NULL
  );
  COMMIT;
END;
/
BEGIN
  DBMS_NETWORK_ACL_ADMIN.ADD_PRIVILEGE (
    acl          => 'hello_utl_http_permissions.xml',
    principal    => '&APP_USER',
    is_grant     => TRUE,
    privilege    => 'resolve',                          -- Povolujeme překlad jmen hostitelů
    start_date   => NULL,
    end_date     => NULL
  );
  COMMIT;
END;
/
BEGIN
  DBMS_NETWORK_ACL_ADMIN.ASSIGN_ACL (
    acl         => 'hello_utl_http_permissions.xml',
    host        => '*',       -- ' * ' znamená všechny hostitele (IP adresy nebo doménová jména)
    lower_port  => NULL,      -- NULL znamená od nejnižšího portu
    upper_port  => NULL       -- NULL znamená do nejvyššího portu (všechny porty)
  );
  COMMIT;
END;
/
COMMIT;
