create or replace package utl_long is
    procedure to_long(source_owner  in varchar2,
                      source_table  in varchar2,
                      source_column in varchar2,
                      source_rowid  in rowid,
                      dest_owner    in varchar2,
                      dest_table    in varchar2,
                      dest_column   in varchar2,
                      dest_rowid    in rowid) as
        language java name 'LongUtils.toLong(java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID, java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID)';

    procedure to_long(source_table  in varchar2,
                      source_column in varchar2,
                      source_rowid  in rowid,
                      dest_table    in varchar2,
                      dest_column   in varchar2,
                      dest_rowid    in rowid) as
        language java name 'LongUtils.toLong(java.lang.String, java.lang.String, oracle.sql.ROWID, java.lang.String, java.lang.String, oracle.sql.ROWID)';

    procedure to_long(source_clob in clob,
                      dest_owner  in varchar2,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid) as
        language java name 'LongUtils.toLong(java.sql.Clob, java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID)';

    procedure to_long(source_clob in clob,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid) as
        language java name 'LongUtils.toLong(java.sql.Clob, java.lang.String, java.lang.String, oracle.sql.ROWID)';

    procedure to_long(source_blob in blob,
                      dest_owner  in varchar2,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid) as
        language java name 'LongUtils.toLong(java.sql.Blob, java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID)';

    procedure to_long(source_blob in blob,
                      dest_table  in varchar2,
                      dest_column in varchar2,
                      dest_rowid  in rowid) as
        language java name 'LongUtils.toLong(java.sql.Blob, java.lang.String, java.lang.String, oracle.sql.ROWID)';

    function substr(owner     in varchar2,
                    tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number,
                    length    in number) return varchar2 as
        language java name 'LongUtils.getSubString(java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID, int, int) return java.lang.String';

    function substr(tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number,
                    length    in number) return varchar2 as
        language java name 'LongUtils.getSubString(java.lang.String, java.lang.String, oracle.sql.ROWID, int, int) return java.lang.String';

    function substr(owner     in varchar2,
                    tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number) return varchar2 as
        language java name 'LongUtils.getSubString(java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID, int) return java.lang.String';

    function substr(tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid,
                    position  in number) return varchar2 as
        language java name 'LongUtils.getSubString(java.lang.String, java.lang.String, oracle.sql.ROWID, int) return java.lang.String';

    function length(owner     in varchar2,
                    tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid) return number as
        language java name 'LongUtils.getLength(java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID) return int';

    function length(tablename in varchar2,
                    column    in varchar2,
                    rowid     in rowid) return number as
        language java name 'LongUtils.getLength(java.lang.String, java.lang.String, oracle.sql.ROWID) return int';

    function lengthb(owner     in varchar2,
                     tablename in varchar2,
                     column    in varchar2,
                     rowid     in rowid) return number as
        language java name 'LongUtils.getLengthBytes(java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID) return int';

    function lengthb(tablename in varchar2,
                     column    in varchar2,
                     rowid     in rowid) return number as
        language java name 'LongUtils.getLengthBytes(java.lang.String, java.lang.String, oracle.sql.ROWID) return int';

    function lengthraw(owner     in varchar2,
                       tablename in varchar2,
                       column    in varchar2,
                       rowid     in rowid) return number as
        language java name 'LongUtils.getLengthRaw(java.lang.String, java.lang.String, java.lang.String, oracle.sql.ROWID) return int';

    function lengthraw(tablename in varchar2,
                       column    in varchar2,
                       rowid     in rowid) return number as
        language java name 'LongUtils.getLengthRaw(java.lang.String, java.lang.String, oracle.sql.ROWID) return int';
end utl_long;
/
