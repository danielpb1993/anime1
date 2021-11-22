CREATE TABLE IF NOT EXISTS anime (
id uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
name text,
description text,
type text,
year_release smallint,
image text
)