# UTL_LONG

Utilities to work with Oracle LONG and LONG RAW data types.
* Copy LONG and LONG RAW data to another table
* Convert CLOB to LONG and BLOB to LONG RAW and copy to another table
* Get substring from a LONG data
* Get length from LONG and LONG RAW data

Datatype conversion on procedures **to_long()** is listed below:
| Source datatype | Destination datatype |
|--|--|
| LONG | LONG |
| CLOB | LONG |
| LONG RAW | LONG RAW |
| BLOB | LONG RAW |

***Note: LONG and LONG RAW are obsolete datatypes. Use CLOB and BLOB datatypes instead.***

Click [here](#Examples) for usage examples.

If this package is usefull for you, please let me know sending an email to alexkirsten@gmail.com.

|List of procedures and functions|
|--|
| procedure [to_long](#to_long)(source_owner  in varchar2, source_table  in varchar2, source_column in varchar2, source_rowid in rowid, dest_owner in varchar2, dest_table in varchar2, dest_column   in varchar2, dest_rowid in rowid); |
|procedure [to_long](#to_long-1)(source_table in varchar2, source_column in varchar2, source_rowid in rowid, dest_table in varchar2, dest_column in varchar2, dest_rowid in rowid);|
|procedure [to_long](#to_long-2)(source_clob in clob, dest_owner in varchar2, dest_table in varchar2, dest_column in varchar2, dest_rowid in rowid);|
|procedure [to_long](#to_long-3)(source_clob in clob, dest_table in varchar2, dest_column in varchar2, dest_rowid in rowid);|
|procedure [to_long](#to_long-4)(source_blob in blob, dest_owner in varchar2, dest_table in varchar2, dest_column in varchar2, dest_rowid in rowid);|
|procedure [to_long](#to_long-5)(source_blob in blob, dest_table in varchar2, dest_column in varchar2, dest_rowid in rowid);|
|function [substr](#substr)(owner in varchar2, tablename in varchar2, column in varchar2, rowid in rowid, position in number, length in number) return varchar2;|
|function [substr](#substr-1)(tablename in varchar2, column in varchar2, rowid in rowid, position in number, length in number) return varchar2;|
|function [substr](#substr-2)(owner in varchar2, tablename in varchar2, column in varchar2, rowid in rowid, position in number) return varchar2;|
|function [substr](#substr-3)(tablename in varchar2, column in varchar2, rowid in rowid, position in number) return varchar2;|
|function [length](#length)(owner in varchar2, tablename in varchar2, column in varchar2, rowid in rowid) return number;|
|function [length](#length-1)(tablename in varchar2, column in varchar2, rowid in rowid) return number;|
|function [lengthb](#lengthb)(owner in varchar2, tablename in varchar2, column in varchar2, rowid in rowid) return number;|
|function [lengthb](#lengthb-1)(tablename in varchar2, column in varchar2, rowid in rowid) return number;|
|function [lengthraw](#lengthraw)(owner in varchar2, tablename in varchar2, column in varchar2, rowid in rowid) return number;|
|function [lengthraw](#lengthraw-1)(tablename in varchar2, column in varchar2, rowid in rowid) return number;|

                                          
## TO_LONG

    procedure to_long(source_owner  in varchar2,
                      source_table  in varchar2,
                      source_column in varchar2,
                      source_rowid  in rowid,
                      dest_owner    in varchar2,
                      dest_table    in varchar2,
                      dest_column   in varchar2,
                      dest_rowid    in rowid);

Copy long, long raw, clob or blob data from a given source owner.table.column to a given long or long raw owner.table.column.

|Parameter| Description
|--|--|
|source_owner  | Owner of table with the source long or long raw column. If parameter is null, then the current user is used. |
| source_table | Table that holds origin long or long raw column
| source_column | Column with source long or long raw data
| source_rowid | Rowid of record that have the origin long or long raw data
|dest_owner  |Owner of table that owns table with destination long or long raw column. If parameter is null, then the current user is used |
| dest_table |Table that holds destination long or long raw column
| dest_column | Column with destination long or long raw data
| dest_rowid | Rowid of record that have the destination  long or long raw data

## TO_LONG

    procedure to_long(source_table  in varchar2,
                      source_column in varchar2,
                      source_rowid  in rowid,
                      dest_table    in varchar2,
                      dest_column   in varchar2,
                      dest_rowid    in rowid);

Copy long, long raw, clob or blob data from a given source table.column to a given long or long raw table.column.

|Parameter| Description
|--|--|
| source_table | Table that holds origin long or long raw column
| source_column | Column with source long or long raw data
| source_rowid | Rowid of record that have the origin long or long raw data
| dest_table |Table that holds destination long or long raw column
| dest_column | Column with destination long or long raw data
| dest_rowid | Rowid of record that have the destination  long or long raw data

## TO_LONG

    procedure to_long(source_clob in clob,
                      dest_owner  in varchar2,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid);

Save a CLOB data from PL/SQL to a given long or long raw owner.table.column.

|Parameter| Description
|--|--|
|source_clob  | CLOB content. Intended to use with PL/SQL. |
|dest_owner  |Owner of table that owns table with destination long or long raw column. If parameter is null, then the current user is used |
| dest_table |Table that holds destination long or long raw column
| dest_column | Column with destination long or long raw data
| dest_rowid | Rowid of record that have the destination  long or long raw data

## TO_LONG

    procedure to_long(source_clob in clob,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid);

Save a CLOB data from PL/SQL to a given long or long raw table.column.

|Parameter| Description
|--|--|
|source_clob  | CLOB content. Intended to use with PL/SQL. |
| dest_table |Table that holds destination long or long raw column
| dest_column | Column with destination long or long raw data
| dest_rowid | Rowid of record that have the destination  long or long raw data

## TO_LONG

    procedure to_long(source_blob in blob,
                      dest_owner  in varchar2,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid);

Save a BLOB data from PL/SQL to a given long or long raw owner.table.column.

|Parameter| Description
|--|--|
|source_blob  | BLOB content. Intended to use with PL/SQL. |
|dest_owner  |Owner of table that owns table with destination long or long raw column. If parameter is null, then the current user is used |
| dest_table |Table that holds destination long or long raw column
| dest_column | Column with destination long or long raw data
| dest_rowid | Rowid of record that have the destination  long or long raw data

## TO_LONG

    procedure to_long(source_blob in blob,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid);

Save a BLOB data from PL/SQL to a given long or long raw table.column.

|Parameter| Description
|--|--|
|source_blob  | BLOB content. Intended to use with PL/SQL. |
| dest_table |Table that holds destination long or long raw column
| dest_column | Column with destination long or long raw data
| dest_rowid | Rowid of record that have the destination  long or long raw data

## SUBSTR

    function substr(owner     in varchar2,
                    tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number,
                    length    in number) return varchar2;

Extract a substring from a string.

This function have the same behavior of Oracle SQL SUBSTR function.

Extracted from documentation of SQL [SUBSTR](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/SUBSTR.html) function:

-   If  `position`  is 0, then it is treated as 1.
    
-   If  `position`  is positive, then Oracle Database counts from the beginning of  `char`  to find the first character.
    
-   If  `position`  is negative, then Oracle counts backward from the end of  `char`.
    
-   If  `substring_length`  is omitted, then Oracle returns all characters to the end of  `char`. If  `substring_length`  is less than 1, then Oracle returns null.

|Parameter| Description
|--|--|
|owner| Owner of table with the source long or long raw column. If parameter is null, then the current user is used. |
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data
| position| Position 
| length |Length of substring

## SUBSTR

    function substr(tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number,
                    length    in number) return varchar2;

Extract a substring from a string.

This function have the same behavior of Oracle SQL SUBSTR function.

Extracted from documentation of SQL [SUBSTR](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/SUBSTR.html) function:

-   If  `position`  is 0, then it is treated as 1.
    
-   If  `position`  is positive, then Oracle Database counts from the beginning of  `char`  to find the first character.
    
-   If  `position`  is negative, then Oracle counts backward from the end of  `char`.
    
-   If  `substring_length`  is omitted, then Oracle returns all characters to the end of  `char`. If  `substring_length`  is less than 1, then Oracle returns null.

|Parameter| Description
|--|--|
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data
| position| Position 
| length |Length of substring

## SUBSTR

    function substr(owner     in varchar2,
                    tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number) return varchar2;

Extract a substring from a string.

This function have the same behavior of Oracle SQL SUBSTR function.

Extracted from documentation of SQL [SUBSTR](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/SUBSTR.html) function:

-   If  `position`  is 0, then it is treated as 1.
    
-   If  `position`  is positive, then Oracle Database counts from the beginning of  `char`  to find the first character.
    
-   If  `position`  is negative, then Oracle counts backward from the end of  `char`.
    
-   If  `substring_length`  is omitted, then Oracle returns all characters to the end of  `char`. If  `substring_length`  is less than 1, then Oracle returns null.

|Parameter| Description
|--|--|
|owner| Owner of table with the source long or long raw column. If parameter is null, then the current user is used. |
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data
| position| Position 

## SUBSTR

    function substr(tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number) return varchar2;

Extract a substring from a string.

This function have the same behavior of Oracle SQL SUBSTR function.

Extracted from documentation of SQL [SUBSTR](https://docs.oracle.com/en/database/oracle/oracle-database/19/sqlrf/SUBSTR.html) function:

-   If  `position`  is 0, then it is treated as 1.
    
-   If  `position`  is positive, then Oracle Database counts from the beginning of  `char`  to find the first character.
    
-   If  `position`  is negative, then Oracle counts backward from the end of  `char`.
    
-   If  `substring_length`  is omitted, then Oracle returns all characters to the end of  `char`. If  `substring_length`  is less than 1, then Oracle returns null.

|Parameter| Description
|--|--|
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data
| position| Position 

## LENGTH

    function length(owner     in varchar2,
                    tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid) return number;

Return the length in characters of a LONG column. If column have a NULL value, then the length returned is 0.

|Parameter| Description
|--|--|
|owner| Owner of table with the source long or long raw column. If parameter is null, then the current user is used. |
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data

## LENGTH

    function length(tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid) return number;

Return the length in characters of a LONG column. If column have a NULL value, then the length returned is 0.

|Parameter| Description
|--|--|
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data

## LENGTHB

    function lengthb(owner     in varchar2,
                     tablename in varchar2,
                     column    in varchar2,
                     rowid     in rowid) return number;

Return the length in bytes of a LONG or a LONG RAW column. If column have a NULL value, then the length returned is 0.

|Parameter| Description
|--|--|
|owner| Owner of table with the source long or long raw column. If parameter is null, then the current user is used. |
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data

## LENGTHB

    function lengthb(tablename in varchar2,
                     column    in varchar2,
                     rowid     in rowid) return number;

Return the length in bytes of a LONG or a LONG RAW column. If column have a NULL value, then the length returned is 0.

|Parameter| Description
|--|--|
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data

## LENGTHRAW

    function lengthraw(owner     in varchar2,
                       tablename in varchar2,
                       column    in varchar2,
                       rowid     in rowid) return number;

Return the length in bytes of a LONG RAW column. If column have a NULL value, then the length returned is 0.

|Parameter| Description
|--|--|
|owner|  Owner of table with the source long or long raw column. If parameter is null, then the current user is used. |
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data

## LENGTHRAW

    function lengthraw(tablename in varchar2,
                       column    in varchar2,
                       rowid     in rowid) return number;

Return the length in bytes of a LONG RAW column. If column have a NULL value, then the length returned is 0.

|Parameter| Description
|--|--|
|tablename| Table that holds source long or long raw column |
| column | Column with source long or long raw data |
| rowid | Rowid of record that have the source long or long raw data

---
# Examples

**Create table and populate with some dummy data**

create table table_source(id number, data clob);<br>
create table table_dest(id number, data long);<br>
insert into table_source values(1,'CLOB Data');<br>
insert into table_dest values(1,null);<br>
commit;<br>


**Example 1: Copy data from table_source.data to table_dest.data**

Source data can be CLOB, BLOB, LONG or LONG RAW.<br>
Destination **must be** LONG or LONG RAW.<br>
Destination record must exists before copy data.<br>
Data in the target column will be overwritten.<br>

	declare
	    src_rowid rowid;
	    dst_rowid rowid;
	begin
	    select rowid 
	      into src_rowid 
	      from table_source 
	     where id = 1;

	    select rowid 
	      into dst_rowid 
	      from table_dest 
	     where id = 1;
	     
	    utl_long.to_long('table_source', 
	                     'data', 
	                     src_rowid, 
	                     'table_dest', 
	                     'data', 
	                     dst_rowid);
	    commit;
	end;


**Example 2: Save CLOB data directly from PL/SQL to a LONG column**

	declare
	    src_clob clob;
	    dst_rowid rowid;
	begin
	    src_clob := 'CLOB from PL/SQL';
	    
	    select rowid 
	      into dst_rowid 
	      from table_dest 
	     where id = 1;
	     
	    utl_long.to_long(src_clob, 'table_dest', 'data', dst_rowid);
	    
	    commit;
	end;

**Example 3: Get a substring from a LONG column**

1) Direct from SQL (LONG column have 'CLOB from PL/SQL' value)

		select utl_long.substr('table_dest', 'data', rowid, 3, 5) from table_dest;
		select utl_long.substr('table_dest', 'data', rowid, 6) from table_dest;

2) Inside PL/SQL

		declare
		    rec_rowid rowid;
		    ret_substring varchar2(10);
		begin
		    select rowid 
		      into rec_rowid 
		      from table_dest 
		     where id = 1;

		    ret_substring := utl_long.substr('table_dest', 'data', rec_rowid, 3, 5);

		    dbms_output.put_line(ret_substring);

		    ret_substring := utl_long.substr('table_dest', 'data', rec_rowid, 6);

		    dbms_output.put_line(ret_substring);
		end;

**Example 4: Length a LONG column data**

1) Direct from SQL (LONG column have 'CLOB from PL/SQL' value)

		select utl_long.length('table_dest', 'data', rowid) from table_dest;

2) Inside PL/SQL

		declare
		    rec_rowid rowid;
		    ret_length number(10);
		begin
		    select rowid 
		      into rec_rowid 
		      from table_dest 
		     where id = 1;

		    ret_length := utl_long.length('table_dest', 'data', rec_rowid);

		    dbms_output.put_line('Length: '||ret_length);
		end;

