package com.yqb.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Author   yaoqinbao
 * Time     2019/5/7
 */
public enum EcIcons implements Icon {
    icon_scan('\ue657'),
    icon_ali_pay('\ue717');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
