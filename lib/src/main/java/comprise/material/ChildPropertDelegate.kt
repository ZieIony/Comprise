package comprise.material

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

class ChildPropertDelegate<T>(var propertyImpl: KMutableProperty<*>) {
    operator fun getValue(any: Any, property: KProperty<*>): T {
        return propertyImpl.getter.call() as T
    }

    operator fun setValue(any: Any, property: KProperty<*>, value: T) {
        propertyImpl.setter.call(value)
    }

}
