# Generated by Django 4.1.5 on 2023-01-25 14:02

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('wypozyczalnia', '0004_initial'),
    ]

    operations = [
        migrations.RenameField(
            model_name='bookissue',
            old_name='card_number',
            new_name='library_user',
        ),
    ]
