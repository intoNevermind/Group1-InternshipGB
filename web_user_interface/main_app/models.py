from django.db import models

# ЗАМЕТКА
# Так как структура базы даных определена не django, а отдельным
# скриптом для PostgreSQL, то для соответствия имён таблиц и колонок
# применяется принудительное именование при помощи Meta.db_table
# и Field.db_column


# Create your models here.
class Persons(models.Model):
    """Таблица базы данных, отвечающая за хранение имен личностей.
    Каждой личности соответствует от 0 до бесконечности ключевых
    слов."""
    
    class Meta:
        db_table = 'persons'
    
    id = models.AutoField(
        db_column='ID',
        primary_key=True
    )
    name = models.CharField(
        db_column='Name',
        max_length=2048,
        unique=True
    )
    active = models.BooleanField(
        db_column='Active',
        default=True
    )


class Keywords(models.Model):
    """Tаблица базы данных, отвечающая за хранение ключевых слов,
    соответствующих каждой конкретной личности."""
    
    class Meta:
        db_table = 'keywords'
    
    id = models.AutoField(
        db_column='ID',
        primary_key=True
    )
    name = models.CharField(
        db_column='Name',
        max_length=2048
    )
    personid = models.ForeignKey(
        Persons,
        db_column='PersonID',
        on_delete=models.CASCADE
    )


class Sites(models.Model):
    """Tаблица базы данных, содержит названия сайтов для анализа
    на упоминания."""
    
    class Meta:
        db_table = 'sites'
    
    id = models.AutoField(
        db_column='ID',
        primary_key=True
    )
    name = models.CharField(
        db_column='Name',
        max_length=2048,
        unique=True
    )
    url = models.CharField(
        db_column='URL',
        max_length=2048
    )
    active = models.BooleanField(
        db_column='Active',
        default=True
    )


class Pages(models.Model):
    """Таблица базы данных, содержит страницы сайта, которые были
    найдены при анализе сайтов из таблицы Sites."""
    
    class Meta:
        db_table = 'pages'
    
    id = models.AutoField(
        db_column='ID',
        primary_key=True
    )
    url = models.CharField(
        db_column='URL',
        max_length=2048
    )
    siteid = models.ForeignKey(
        Sites,
        db_column='SiteID',
        on_delete=models.CASCADE
    )
    founddatetime = models.DateTimeField(
        db_column='FoundDateTime'
    )
    lastscandate = models.DateTimeField(
        db_column='LastScanDate',
        null=True
    )


class PersonPageRank(models.Model):
    """Таблица базы данных, отвечающая за хранение количества упоминаний
    каждой личности на обработанной странице."""
    
    class Meta:
        db_table = 'personpagerank'
    
    personid = models.ForeignKey(
        Persons,
        db_column='PersonID',
        on_delete=models.CASCADE
    )
    pageid = models.ForeignKey(
        Pages,
        db_column='PageID',
        on_delete=models.CASCADE
    )
    rank = models.IntegerField(
        db_column='Rank'
    )

