CREATE TABLE Counter(
	id int IDENTITY(1,1) NOT NULL,
	name nvarchar(255) NOT NULL,
	value int NOT NULL DEFAULT 0,
PRIMARY KEY CLUSTERED
(
	[id] ASC
) WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY];
GO

ALTER TABLE Counter ADD CONSTRAINT UQ_Counter_name UNIQUE (name);
GO
