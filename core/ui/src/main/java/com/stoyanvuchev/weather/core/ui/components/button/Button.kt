/*
 * MIT License
 *
 * Copyright (c) 2025 Stoyan Vuchev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.stoyanvuchev.weather.core.ui.components.button

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.weather.core.ui.components.interaction.rememberRipple
import com.stoyanvuchev.weather.core.ui.theme.Theme
import com.stoyanvuchev.weather.core.ui.theme.color.LocalColor
import com.stoyanvuchev.weather.core.ui.theme.shape.ShapeData
import com.stoyanvuchev.weather.core.ui.theme.typography.LocalTextStyle

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = defaultButtonColors(),
    shapeData: ShapeData = Theme.shapes.large,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {

    val transition = updateTransition(
        targetState = enabled,
        label = "ButtonEnabledUpdateTransition"
    )

    val containerColor by transition.animateColor(
        targetValueByState = { colors.containerColor(it) }
    )

    val contentColor by transition.animateColor(
        targetValueByState = { colors.contentColor(it) }
    )

    CompositionLocalProvider(
        LocalColor provides contentColor,
        LocalTextStyle provides Theme.typography.labelMedium
    ) {

        Row(
            modifier = modifier
                .clip(shape = shapeData.shape)
                .background(color = containerColor)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = rememberRipple(color = contentColor),
                )
                .padding(
                    horizontal = ButtonHorizontalPadding,
                    vertical = ButtonVerticalPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterHorizontally
            ),
            content = content
        )

    }

}

@Stable
@Composable
private fun defaultButtonColors() = ButtonColors(
    containerColor = Theme.colorPalette.primary,
    contentColor = Theme.colorPalette.onPrimary,
    disabledContainerColor = Theme.colorPalette.primary.copy(.33f),
    disabledContentColor = Theme.colorPalette.onPrimary.copy(.33f)
)

private val ButtonHorizontalPadding: Dp get() = 18.dp
private val ButtonVerticalPadding: Dp get() = 12.dp