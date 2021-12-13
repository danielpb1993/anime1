CREATE TABLE IF NOT EXISTS anime (
id uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
name text,
description text,
type text,
year_release smallint,
image text);

CREATE TABLE usser (
userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
username varchar(24) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
role varchar(10),
enabled boolean DEFAULT true);

CREATE TABLE favorite (
userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
id uuid REFERENCES anime(id) ON DELETE CASCADE,
PRIMARY KEY(userid, id));