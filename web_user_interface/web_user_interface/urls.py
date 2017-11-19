"""web_user_interface URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from main_app import views as main_app_views

admin.autodiscover()  #функция автоматического обнаружения файлов admin.py в наших приложения

urlpatterns = [
    url(
        r'^admin/',
        admin.site.urls
    ),
    url(
        r'^$',
        main_app_views.main,
        name='main'
    ),
    # Статистики
    url(
        r'^statglobal/',
        main_app_views.statistics_global,
        name='statistics_global'
    ),
    url(
        r'^statbydays/',
        main_app_views.statistics_by_days,
        name='statistics_by_days'
    ),
    # Предпочтения пользователей
    url(
        r'^prefpersons/',
        main_app_views.preferences_persons,
        name='preferences_persons'
    ),
    url(
        r'^prefkeywords/',
        main_app_views.preferences_keywords,
        name='preferences_keywords'
    ),
    url(
        r'^prefsites/',
        main_app_views.preferences_sites,
        name='preferences_sites'
    ),
    # Общие настройки
    url(
        r'^cfgusers/',
        main_app_views.config_users,
        name='config_users'
    ),
]
